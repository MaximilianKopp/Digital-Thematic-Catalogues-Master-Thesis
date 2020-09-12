package com.ataraxia.gabriel_vz.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ataraxia.gabriel_vz.factory.DiscographyFactory
import com.ataraxia.gabriel_vz.model.Discography
import com.ataraxia.gabriel_vz.repository.DiscographyRepository
import com.ataraxia.gabriel_vz.root.Service

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
        discographyFactory
                .modelFromEntity(discographyEntity)
                .right()
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
}