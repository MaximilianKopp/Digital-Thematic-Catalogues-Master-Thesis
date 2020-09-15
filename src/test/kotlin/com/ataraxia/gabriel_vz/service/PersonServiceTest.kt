package com.ataraxia.gabriel_vz.service

import arrow.core.right
import com.ataraxia.gabriel_vz.factory.PersonFactory
import com.ataraxia.gabriel_vz.model.Person
import com.ataraxia.gabriel_vz.persistence.PersonEntity
import com.ataraxia.gabriel_vz.repository.PersonRepository
import io.kotlintest.assertions.arrow.either.shouldBeLeft
import io.kotlintest.assertions.arrow.either.shouldBeRight
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.time.OffsetDateTime
import java.util.*

@TestInstance(PER_CLASS)
internal class PersonServiceTest {

    private val personFactory: PersonFactory = mockkClass(PersonFactory::class)
    private val personRepository: PersonRepository = mockkClass(PersonRepository::class)
    private val personService: PersonService = PersonService(personFactory, personRepository)

    private fun simplePerson() = Person(
            id = "",
            created = OffsetDateTime.now(),
            modified = null,
            title = "Person",
            description = "Pianist",
            name = "Alberto",
            relatedWorks = mutableSetOf(),
            relatedDiscographies = mutableSetOf(),
            pnd = "DNB-2312",
            role = "Musician"
    )

    private fun simplePersonEntity() = PersonEntity(
            id = "",
            created = OffsetDateTime.now(),
            modified = null,
            title = "Person",
            description = "Pianist",
            name = "Alberto",
            relatedWorks = mutableSetOf(),
            relatedDiscographies = mutableSetOf(),
            pnd = "DNB-2312",
            role = "Musician"
    )

    @BeforeEach
    fun setUp() {
        every { personFactory.modelFromEntity(simplePersonEntity()) } returns simplePerson()
        every { personFactory.entityFromModel(simplePerson()) } returns simplePersonEntity()
        every { personRepository.findAll() } returns mutableListOf(simplePersonEntity())
        every { personRepository.findById(simplePerson().id!!) } returns Optional.of(simplePersonEntity())
        every { personRepository.save(simplePersonEntity()) } returns simplePersonEntity()
    }

    @AfterEach
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun `get all Incipits`() {
        val result = personService.getAll()
        verify(exactly = 1) { personRepository.findAll() }
        verify(exactly = 1) { personFactory.modelFromEntity(simplePersonEntity()) }
        confirmVerified(personRepository)
        confirmVerified(personRepository)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get all Incipits`() {
        every { personRepository.findAll() } throws Exception()
        val result = personService.getAll()
        result.shouldBeLeft()
    }

    @Test
    fun `get Incipit by Id`() {
        val result = personService.get(simplePerson().id!!)
        verify(exactly = 1) { personRepository.findById(simplePerson().id!!) }
        verify(exactly = 1) { personFactory.modelFromEntity(simplePersonEntity()) }
        confirmVerified(personRepository)
        confirmVerified(personFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get Incipit by Id`() {
        every { personRepository.findById(simplePerson().id!!) } throws Exception()
        val result = personService.get(simplePerson().id!!)
        result.shouldBeLeft()
    }

    @Test
    fun `create an Incipit`() {
        val result = personService.create(simplePerson())
        verify(exactly = 1) { personRepository.save(simplePersonEntity()) }
        verify(exactly = 1) { personFactory.entityFromModel(simplePerson()) }
        verify(exactly = 1) { personFactory.modelFromEntity(simplePersonEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception create an Incipit`() {
        every { personRepository.save(simplePersonEntity()) } throws Exception()
        val result = personService.create(simplePerson())
        result.shouldBeLeft()
    }

    @Test
    fun `update an Incipit`() {
        val result = personService.update(simplePerson().id!!, simplePerson())
        verify(exactly = 1) { personFactory.entityFromModel(simplePerson()) }
        verify(exactly = 1) { personFactory.modelFromEntity(simplePersonEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception update Incipit`() {
        every { personRepository.findById(simplePerson().id!!) } throws Exception()
        val result = personService.update(simplePerson().id!!, simplePerson())
        result.shouldBeLeft()
    }

    @Test
    fun `delete all Incipits`() {
        val result = personService.deleteAll()
        verify(exactly = 1) { personRepository.deleteAll() }
        result.right()
    }

    @Test
    fun `delete an Incipit by id`() {
        val result = personService.delete(simplePerson().id!!)
        verify(exactly = 1) { personRepository.deleteById(simplePerson().id!!) }
        result.right()
    }

}