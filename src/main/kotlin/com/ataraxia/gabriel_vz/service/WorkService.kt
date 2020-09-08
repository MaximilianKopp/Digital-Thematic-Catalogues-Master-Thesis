package com.ataraxia.gabriel_vz.service

import com.ataraxia.gabriel_vz.factory.WorkFactory
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.persistence.WorkEntity
import com.ataraxia.gabriel_vz.repository.WorkRepository
import com.ataraxia.gabriel_vz.root.Service

@org.springframework.stereotype.Service
class WorkService(
        private val workRepository: WorkRepository,
        private val workFactory: WorkFactory
) : Service<Work>() {

    override fun getAll(): MutableList<Work> = workRepository
            .findAll().map {
                workFactory.modelFromEntity(it)
            }.toMutableList()

    override fun get(id: String): Work = workFactory
            .modelFromEntity(workRepository.findById(id).get())

    override fun create(work: Work): Work {
        val workEntity = workRepository.save(workFactory.entityFromModel(work))
        return workFactory.modelFromEntity(workEntity)
    }

    override fun update(id: String, work: Work): Work {
        val workData = workRepository.findById(id)
        val workEntity = workFactory.entityFromModel(work)
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
        val result = workRepository.save(updatedWorkEntity)
        return workFactory.modelFromEntity(result)
    }

    override fun deleteAll() = workRepository.deleteAll()

    override fun delete(id: String) = workRepository.deleteById(id)
}