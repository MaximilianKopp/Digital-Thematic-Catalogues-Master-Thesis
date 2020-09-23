package com.ataraxia.gabriel_vz.service

import arrow.core.right
import com.ataraxia.gabriel_vz.factory.IncipitFactory
import com.ataraxia.gabriel_vz.model.Incipit
import com.ataraxia.gabriel_vz.persistence.IncipitEntity
import com.ataraxia.gabriel_vz.repository.IncipitRepository
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
internal class IncipitServiceTest {

    private val incipitFactory: IncipitFactory = mockkClass(IncipitFactory::class)
    private val incipitRepository: IncipitRepository = mockkClass(IncipitRepository::class)
    private val incipitService: IncipitService = IncipitService(incipitFactory, incipitRepository)

    private fun simpleIncipit() = Incipit(
            id = "",
            created = OffsetDateTime.now(),
            modified = null,
            title = "Incipit",
            text = "Flowers in the field",
            description = "Text aus Gabriels Liederkreis",
            clef = "Violin",
            score = "f-a-g",
            timesig = "2/4",
            keysig = "g"
    )

    private fun simpleIncipitEntity() = IncipitEntity(
            id = "",
            created = OffsetDateTime.now(),
            modified = null,
            title = "Incipit",
            text = "Flowers in the field",
            description = "Text aus Gabriels Liederkreis",
            clef = "Violin",
            score = "f-a-g",
            timesig = "2/4",
            keysig = "g"
    )

    @BeforeEach
    fun setUp() {
        every { incipitFactory.modelFromEntity(simpleIncipitEntity()) } returns simpleIncipit()
        every { incipitFactory.entityFromModel(simpleIncipit()) } returns simpleIncipitEntity()
        every { incipitRepository.findAll() } returns mutableListOf(simpleIncipitEntity())
        every { incipitRepository.findById(simpleIncipit().id!!) } returns Optional.of(simpleIncipitEntity())
        every { incipitRepository.save(simpleIncipitEntity()) } returns simpleIncipitEntity()
    }

    @AfterEach
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun `get all Incipits`() {
        val result = incipitService.getAll()
        verify(exactly = 1) { incipitRepository.findAll() }
        verify(exactly = 1) { incipitFactory.modelFromEntity(simpleIncipitEntity()) }
        confirmVerified(incipitRepository)
        confirmVerified(incipitFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get all Incipits`() {
        every { incipitRepository.findAll() } throws Exception()
        val result = incipitService.getAll()
        result.shouldBeLeft()
    }

    @Test
    fun `get Incipit by Id`() {
        val result = incipitService.get(simpleIncipit().id!!)
        verify(exactly = 1) { incipitRepository.findById(simpleIncipit().id!!) }
        verify(exactly = 1) { incipitFactory.modelFromEntity(simpleIncipitEntity()) }
        confirmVerified(incipitRepository)
        confirmVerified(incipitFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get Incipit by Id`() {
        every { incipitRepository.findById(simpleIncipit().id!!) } throws Exception()
        val result = incipitService.get(simpleIncipit().id!!)
        result.shouldBeLeft()
    }

    @Test
    fun `create an Incipit`() {
        val result = incipitService.create(simpleIncipit())
        verify(exactly = 1) { incipitRepository.save(simpleIncipitEntity()) }
        verify(exactly = 1) { incipitFactory.entityFromModel(simpleIncipit()) }
        verify(exactly = 1) { incipitFactory.modelFromEntity(simpleIncipitEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception create an Incipit`() {
        every { incipitRepository.save(simpleIncipitEntity()) } throws Exception()
        val result = incipitService.create(simpleIncipit())
        result.shouldBeLeft()
    }

    @Test
    fun `update an Incipit`() {
        val result = incipitService.update(simpleIncipit().id!!, simpleIncipit())
        verify(exactly = 1) { incipitFactory.entityFromModel(simpleIncipit()) }
        verify(exactly = 1) { incipitFactory.modelFromEntity(simpleIncipitEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception update Incipit`() {
        every { incipitRepository.findById(simpleIncipit().id!!) } throws Exception()
        val result = incipitService.update(simpleIncipit().id!!, simpleIncipit())
        result.shouldBeLeft()
    }

    @Test
    fun `delete all Incipits`() {
        val result = incipitService.deleteAll()
        verify(exactly = 1) { incipitRepository.deleteAll() }
        result.right()
    }

    @Test
    fun `delete an Incipit by id`() {
        val result = incipitService.delete(simpleIncipit().id!!)
        verify(exactly = 1) { incipitRepository.deleteById(simpleIncipit().id!!) }
        result.right()
    }

}