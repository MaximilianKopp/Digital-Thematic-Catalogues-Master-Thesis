package com.ataraxia.gabriel_vz.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ataraxia.gabriel_vz.factory.WorkFactory
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.repository.WorkRepository
import com.ataraxia.gabriel_vz.root.Service

@org.springframework.stereotype.Service
class WorkService(
        private val workRepository: WorkRepository,
        private val workFactory: WorkFactory
) : Service<Work>() {

    override fun getAll(): Either<Exception, MutableList<Work>> = try {
        workRepository.findAll()
                .map(workFactory::modelFromEntity)
                .toMutableList()
                .right()
    } catch (e: Exception) {
        e.left()
    }


    override fun get(id: String): Either<Exception, Work> = try {
        workRepository.findById(id)
                .map(workFactory::modelFromEntity)
                .get()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun create(m: Work): Either<Exception, Work> = try {
        val workEntity = workRepository.save(workFactory.entityFromModel(m))
        workFactory.modelFromEntity(workEntity).right()
    } catch (e: Exception) {
        e.left()
    }

    override fun update(id: String, m: Work): Either<Exception, Work> = try {
        val workData = workRepository.findById(id)
        val workEntity = workFactory.entityFromModel(m)
        val updatedWorkEntity = workData.get()
                .apply {
                    this.title = workEntity.title
                    this.dateOfCreation = workEntity.dateOfCreation
                    this.dateOfPremiere = workEntity.dateOfPremiere
                    this.placeOfPremiere = workEntity.placeOfPremiere
                    this.incipit = workEntity.incipit
                    this.commentary = workEntity.commentary
                    this.dedication = workEntity.dedication
                    this.instrumentation = workEntity.instrumentation
                    this.category = workEntity.category
                    this.duration = workEntity.duration
                    this.editor = workEntity.editor
                    this.relatedText = workEntity.relatedText
                    this.discographies = workEntity.discographies
                    this.relatedPersons = workEntity.relatedPersons
                    this.literatureList = workEntity.literatureList
                }
        workFactory.modelFromEntity(updatedWorkEntity).right()
    } catch (e: Exception) {
        e.left()
    }

    override fun deleteAll() = try {
        workRepository.deleteAll().right()
    } catch (e: Exception) {
        e.left()
    }

    override fun delete(id: String) = try {
        workRepository.deleteById(id).right()
    } catch (e: Exception) {
        e.left()
    }
}