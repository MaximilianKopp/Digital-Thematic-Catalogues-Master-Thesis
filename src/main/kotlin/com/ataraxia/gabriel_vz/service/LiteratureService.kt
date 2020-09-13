package com.ataraxia.gabriel_vz.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ataraxia.gabriel_vz.factory.LiteratureFactory
import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.repository.LiteratureRepository
import com.ataraxia.gabriel_vz.root.Service

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
                    this.relatedWork = literatureEntity.relatedWork
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
}