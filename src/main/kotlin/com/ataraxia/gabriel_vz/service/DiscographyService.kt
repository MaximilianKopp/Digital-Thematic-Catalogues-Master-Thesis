package com.ataraxia.gabriel_vz.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ataraxia.gabriel_vz.controller.paging.Paged
import com.ataraxia.gabriel_vz.controller.paging.Paging
import com.ataraxia.gabriel_vz.factory.DiscographyFactory
import com.ataraxia.gabriel_vz.model.Discography
import com.ataraxia.gabriel_vz.repository.DiscographyRepository
import com.ataraxia.gabriel_vz.root.Service
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@org.springframework.stereotype.Service
class DiscographyService(
        private val discographyFactory: DiscographyFactory,
        private val discographyRepository: DiscographyRepository
) : Service<Discography>() {

    override fun getAll(): Either<Exception, MutableList<Discography>> = try {
        discographyRepository.findAll()
                .map(discographyFactory::modelFromEntity)
                .toMutableList()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun get(id: String): Either<Exception, Discography> = try {
        discographyRepository.findById(id)
                .map(discographyFactory::modelFromEntity)
                .get()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun create(m: Discography): Either<Exception, Discography> = try {
        val discographyEntity = discographyRepository.save(discographyFactory.entityFromModel(m))
        discographyFactory.modelFromEntity(discographyEntity).right()
    } catch (e: Exception) {
        e.left()
    }

    override fun update(id: String, m: Discography): Either<Exception, Discography> = try {
        val discographyData = discographyRepository.findById(id)
        val discographyEntity = discographyFactory.entityFromModel(m)
        val updatedDiscographyEntity = discographyData.get()
                .apply {
                    this.title = discographyEntity.title
                    this.dateOfPublishing = discographyEntity.dateOfPublishing
                    this.label = discographyEntity.label
                    this.recordId = discographyEntity.recordId
                    this.musicians = discographyEntity.musicians
                    this.relatedWorks = discographyEntity.relatedWorks
                }
        discographyRepository.save(updatedDiscographyEntity)
        discographyFactory
                .modelFromEntity(updatedDiscographyEntity)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun deleteAll(): Either<Exception, Unit> = try {
        discographyRepository
                .deleteAll()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun delete(id: String): Either<Exception, Unit> = try {
        discographyRepository
                .deleteById(id)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun getPage(pageNumber: Int, size: Int): Paged<Discography> {
        val request: PageRequest = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC, "id")
        val discographyPage = discographyRepository.findAll(request)
                .map(discographyFactory::modelFromEntity)
        return Paged(discographyPage, Paging.of(discographyPage.totalPages, pageNumber, size))
    }
}