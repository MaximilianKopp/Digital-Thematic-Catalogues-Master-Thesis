package com.ataraxia.gabriel_vz.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ataraxia.gabriel_vz.controller.paging.Paged
import com.ataraxia.gabriel_vz.controller.paging.Paging
import com.ataraxia.gabriel_vz.factory.IncipitFactory
import com.ataraxia.gabriel_vz.model.Incipit
import com.ataraxia.gabriel_vz.repository.IncipitRepository
import com.ataraxia.gabriel_vz.root.Service
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@org.springframework.stereotype.Service
class IncipitService(
        private val incipitFactory: IncipitFactory,
        private val incipitRepository: IncipitRepository
) : Service<Incipit>() {

    override fun getAll(): Either<Exception, MutableList<Incipit>> = try {
        incipitRepository.findAll()
                .map(incipitFactory::modelFromEntity)
                .toMutableList()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun get(id: String): Either<Exception, Incipit> = try {
        incipitRepository.findById(id)
                .map(incipitFactory::modelFromEntity)
                .get()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun create(m: Incipit): Either<Exception, Incipit> = try {
        val incipitEntity = incipitRepository.save(incipitFactory.entityFromModel(m))
        incipitFactory
                .modelFromEntity(incipitEntity)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun update(id: String, m: Incipit): Either<Exception, Incipit> = try {
        val incipitData = incipitRepository.findById(id)
        val incipitEntity = incipitFactory.entityFromModel(m)
        val updatedIncipitEntity = incipitData.get()
                .apply {
                    this.description = incipitEntity.description
                    this.keysig = incipitEntity.keysig
                    this.score = incipitEntity.score
                    this.text = incipitEntity.text
                    this.timesig = incipitEntity.timesig
                    this.relatedWork = incipitEntity.relatedWork
                }
        incipitFactory
                .modelFromEntity(updatedIncipitEntity)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun deleteAll(): Either<Exception, Unit> = try {
        incipitRepository
                .deleteAll()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun delete(id: String): Either<Exception, Unit> = try {
        incipitRepository
                .deleteById(id)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun getPage(pageNumber: Int, size: Int): Paged<Incipit> {
        val request: PageRequest = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC, "id")
        val incipitPage = incipitRepository.findAll(request)
                .map(incipitFactory::modelFromEntity)
        return Paged(incipitPage, Paging.of(incipitPage.totalPages, pageNumber, size))
    }
}