package com.ataraxia.gabriel_vz.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ataraxia.gabriel_vz.controller.paging.Paged
import com.ataraxia.gabriel_vz.controller.paging.Paging
import com.ataraxia.gabriel_vz.factory.LiteratureFactory
import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.repository.LiteratureRepository
import com.ataraxia.gabriel_vz.root.Service
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@org.springframework.stereotype.Service
class LiteratureService(
        private val literatureFactory: LiteratureFactory,
        private val literatureRepository: LiteratureRepository
) : Service<Literature>() {

    override fun getAll(): Either<Exception, MutableList<Literature>> = try {
        literatureRepository.findAll()
                .map(literatureFactory::modelFromEntity)
                .toMutableList()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun get(id: String): Either<Exception, Literature> = try {
        literatureRepository.findById(id)
                .map(literatureFactory::modelFromEntity)
                .get()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun create(m: Literature): Either<Exception, Literature> = try {
        val literatureEntity = literatureRepository.save(literatureFactory.entityFromModel(m))
        literatureFactory
                .modelFromEntity(literatureEntity)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun update(id: String, m: Literature): Either<Exception, Literature> = try {
        val literatureData = literatureRepository.findById(id)
        val literatureEntity = literatureFactory.entityFromModel(m)
        val updatedLiteratureEntity = literatureData.get()
                .apply {
                    this.author = literatureEntity.author
                    this.isbn = literatureEntity.isbn
                    this.yearOfPublishing = literatureEntity.yearOfPublishing
                    this.relatedWorks = literatureEntity.relatedWorks
                }
        literatureFactory
                .modelFromEntity(updatedLiteratureEntity)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun deleteAll(): Either<Exception, Unit> = try {
        literatureRepository
                .deleteAll()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun delete(id: String): Either<Exception, Unit> = try {
        literatureRepository
                .deleteById(id)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun getPage(pageNumber: Int, size: Int): Paged<Literature> {
        val request: PageRequest = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC, "id")
        val literaturePage = literatureRepository.findAll(request)
                .map(literatureFactory::modelFromEntity)
        return Paged(literaturePage, Paging.of(literaturePage.totalPages, pageNumber, size))
    }
}