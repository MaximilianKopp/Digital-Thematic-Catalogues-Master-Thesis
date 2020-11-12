package com.ataraxia.gabriel_vz.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ataraxia.gabriel_vz.controller.paging.Paged
import com.ataraxia.gabriel_vz.controller.paging.Paging
import com.ataraxia.gabriel_vz.factory.PersonFactory
import com.ataraxia.gabriel_vz.model.Person
import com.ataraxia.gabriel_vz.repository.PersonRepository
import com.ataraxia.gabriel_vz.root.Service
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@org.springframework.stereotype.Service
class PersonService(
        private val personFactory: PersonFactory,
        private val personRepository: PersonRepository
) : Service<Person>() {

    override fun getAll(): Either<Exception, MutableList<Person>> = try {
        personRepository.findAll()
                .map(personFactory::modelFromEntity)
                .toMutableList()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun get(id: String): Either<Exception, Person> = try {
        personRepository.findById(id)
                .map(personFactory::modelFromEntity)
                .get()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun create(m: Person): Either<Exception, Person> = try {
        val personEntity = personRepository.save(personFactory.entityFromModel(m))
        personFactory
                .modelFromEntity(personEntity)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun update(id: String, m: Person): Either<Exception, Person> = try {
        val personData = personRepository.findById(id)
        val personEntity = personFactory.entityFromModel(m)
        val updatedPersonEntity = personData.get()
                .apply {
                    this.title = personEntity.title
                    this.name = personEntity.name
                    this.description = personEntity.description
                    this.pnd = personEntity.pnd
                    this.role = personEntity.role
                    this.relatedDiscographies = personEntity.relatedDiscographies
                    this.relatedWorks = personEntity.relatedWorks
                }
        personRepository.save(updatedPersonEntity)
        personFactory
                .modelFromEntity(updatedPersonEntity)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun deleteAll(): Either<Exception, Unit> = try {
        personRepository
                .deleteAll()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun delete(id: String): Either<Exception, Unit> = try {
        personRepository
                .deleteById(id)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun getPage(pageNumber: Int, size: Int): Paged<Person> {
        val request: PageRequest = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC, "id")
        val personPage = personRepository.findAll(request)
                .map(personFactory::modelFromEntity)
        return Paged(personPage, Paging.of(personPage.totalPages, pageNumber, size))
    }
}