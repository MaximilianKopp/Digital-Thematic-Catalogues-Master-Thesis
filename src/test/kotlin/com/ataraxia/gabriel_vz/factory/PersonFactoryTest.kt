package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Person
import com.ataraxia.gabriel_vz.persistence.PersonEntity
import com.ataraxia.gabriel_vz.resource.PersonResource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@TestInstance(PER_CLASS)
internal class PersonFactoryTest {

    private val personID = UUID.randomUUID().toString()
    private var personFactory: PersonFactory = PersonFactory()

    private fun simplePerson() = Person(
            id = personID,
            description = "Pianist",
            name = "Alberto",
            relatedWorks = mutableSetOf(),
            relatedDiscographies = mutableSetOf(),
            pnd = "DNB-2312",
            role = "Musician"
    )

    private fun simplePersonEntity() = PersonEntity(
            id = personID,
            description = "Pianist",
            name = "Alberto",
            relatedWorks = mutableSetOf(),
            relatedDiscographies = mutableSetOf(),
            pnd = "DNB-2312",
            role = "Musician"
    )

    private fun simplePersonResource() = PersonResource(
            self = null,
            id = personID,
            description = "Pianist",
            name = "Alberto",
            relatedWorks = mutableSetOf(),
            relatedDiscographies = mutableSetOf(),
            pnd = "DNB-2312",
            role = "Musician"
    )

    @Test
    fun `personModel from personEntity`() {
        val personEntity = simplePersonEntity()
        val person = personFactory.modelFromEntity(personEntity)

        assertNotNull(person)
        assertEquals(person.id, personEntity.id)
        assertEquals(person.description, personEntity.description)
        assertEquals(person.name, personEntity.name)
        assertEquals(person.pnd, personEntity.pnd)
        assertEquals(person.role, personEntity.role)
        person.shouldBeInstanceOf<Person>()
    }

    @Test
    fun `personEntity from personModel`() {
        val person = simplePerson()
        val personEntity = personFactory.entityFromModel(person)

        assertNotNull(person)
        assertEquals(personEntity.id, person.id)
        assertEquals(personEntity.description, person.description)
        assertEquals(personEntity.name, person.name)
        assertEquals(personEntity.pnd, person.pnd)
        assertEquals(personEntity.role, person.role)
        personEntity.shouldBeInstanceOf<PersonEntity>()
    }

    @Test
    fun `personModel from personResource`() {
        val personResource = simplePersonResource()
        val person = personFactory.modelFromResource(personResource)

        assertNotNull(person)
        assertEquals(person.id, personResource.id)
        assertEquals(person.description, personResource.description)
        assertEquals(person.name, personResource.name)
        assertEquals(person.pnd, personResource.pnd)
        assertEquals(person.role, personResource.role)
        person.shouldBeInstanceOf<Person>()
    }

    @Test
    fun `personResource from personModel`() {
        val person = simplePerson()
        val personResource = personFactory.resourceFromModel(person)

        assertNotNull(person)
        assertEquals(personResource.id, person.id)
        assertEquals(personResource.description, person.description)
        assertEquals(personResource.name, person.name)
        assertEquals(personResource.pnd, person.pnd)
        assertEquals(personResource.role, person.role)
    }

}