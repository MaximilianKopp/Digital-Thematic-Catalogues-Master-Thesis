package com.ataraxia.gabriel_vz.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ataraxia.gabriel_vz.controller.paging.Paged
import com.ataraxia.gabriel_vz.controller.paging.Paging
import com.ataraxia.gabriel_vz.factory.PlaceFactory
import com.ataraxia.gabriel_vz.model.Place
import com.ataraxia.gabriel_vz.repository.PlaceRepository
import com.ataraxia.gabriel_vz.root.Service
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@org.springframework.stereotype.Service
class PlaceService(
        private val placeFactory: PlaceFactory,
        private val placeRepository: PlaceRepository
) : Service<Place>() {

    override fun getAll(): Either<Exception, MutableList<Place>> = try {
        placeRepository.findAll()
                .map(placeFactory::modelFromEntity)
                .toMutableList()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun get(id: String): Either<Exception, Place> = try {
        placeRepository.findById(id)
                .map(placeFactory::modelFromEntity)
                .get()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun create(m: Place): Either<Exception, Place> = try {
        val place = placeFactory.entityFromModel(m)
        placeRepository.save(place)
        placeFactory
                .modelFromEntity(place)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun update(id: String, m: Place): Either<Exception, Place> = try {
        val placeData = placeRepository.findById(id)
        val placeEntity = placeFactory.entityFromModel(m)
        val updatedPlaceEntity = placeData.get()
                .apply {
                    this.title = placeEntity.title
                    this.name = placeEntity.name
                    this.country = placeEntity.country
                    this.locality = placeEntity.locality
                    this.coordinates = placeEntity.coordinates
                    this.relatedWorks = placeEntity.relatedWorks
                }
        placeRepository.save(updatedPlaceEntity)
        placeFactory
                .modelFromEntity(updatedPlaceEntity)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun deleteAll(): Either<Exception, Unit> = try {
        placeRepository
                .deleteAll()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun delete(id: String): Either<Exception, Unit> = try {
        placeRepository
                .deleteById(id)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun getPage(pageNumber: Int, size: Int): Paged<Place> {
        val request: PageRequest = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC, "id")
        val placePage = placeRepository.findAll(request)
                .map(placeFactory::modelFromEntity)
        return Paged(placePage, Paging.of(placePage.totalPages, pageNumber, size))
    }
}