package com.ataraxia.gabriel_vz.service

import arrow.core.right
import com.ataraxia.gabriel_vz.factory.WorkFactory
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.persistence.WorkEntity
import com.ataraxia.gabriel_vz.repository.WorkRepository
import io.kotlintest.assertions.arrow.either.shouldBeLeft
import io.kotlintest.assertions.arrow.either.shouldBeRight
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.util.*

@TestInstance(PER_CLASS)
internal class WorkServiceTest {

    private val workFactory: WorkFactory = mockkClass(WorkFactory::class)
    private val workRepository: WorkRepository = mockkClass(WorkRepository::class)
    private val workService: WorkService = WorkService(workRepository, workFactory)

    private fun simpleWork() = Work(
            id = "",
            title = "Sonata C-Dur",
            category = "Klaviermusik",
            commentary = "Tolles Werk",
            dateOfCreation = "23.12.1989",
            dateOfPremiere = "26.09.2010",
            dedication = "Für mich",
            placeOfPremiere = null,
            duration = "2 Minuten",
            editor = "Max",
            relatedText = null,
            instrumentation = "Für Klavier",
            literatureList = mutableSetOf(),
            relatedPersons = mutableSetOf(),
            discographies = mutableSetOf()
    )

    private fun simpleWorkEntity() = WorkEntity(
            id = "",
            title = "Sonata C-Dur",
            category = "Klaviermusik",
            commentary = "Tolles Werk",
            dateOfCreation = "23.12.1989",
            dateOfPremiere = "26.09.2010",
            dedication = "Für mich",
            placeOfPremiere = null,
            duration = "2 Minuten",
            editor = "Max",
            relatedText = null,
            instrumentation = "Für Klavier",
            literatureList = mutableSetOf(),
            relatedPersons = mutableSetOf(),
            discographies = mutableSetOf()
    )

    @BeforeEach
    fun setUp() {
        every { workFactory.modelFromEntity(simpleWorkEntity()) } returns simpleWork()
        every { workFactory.entityFromModel(simpleWork()) } returns simpleWorkEntity()
        every { workRepository.findAll() } returns mutableListOf(simpleWorkEntity())
        every { workRepository.findById(simpleWork().id!!) } returns Optional.of(simpleWorkEntity())
        every { workRepository.save(simpleWorkEntity()) } returns simpleWorkEntity()
    }

    @AfterEach
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun `get all Texts`() {
        val result = workService.getAll()
        verify(exactly = 1) { workRepository.findAll() }
        verify(exactly = 1) { workFactory.modelFromEntity(simpleWorkEntity()) }
        confirmVerified(workRepository)
        confirmVerified(workFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get all Texts`() {
        every { workRepository.findAll() } throws Exception()
        val result = workService.getAll()
        result.shouldBeLeft()
    }

    @Test
    fun `get a Text by Id`() {
        val result = workService.get(simpleWork().id!!)
        verify(exactly = 1) { workRepository.findById(simpleWork().id!!) }
        verify(exactly = 1) { workFactory.modelFromEntity(simpleWorkEntity()) }
        confirmVerified(workRepository)
        confirmVerified(workFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get a Text by Id`() {
        every { workRepository.findById(simpleWork().id!!) } throws Exception()
        val result = workService.get(simpleWork().id!!)
        result.shouldBeLeft()
    }

    @Test
    fun `create a Text`() {
        val result = workService.create(simpleWork())
        verify(exactly = 1) { workRepository.save(simpleWorkEntity()) }
        verify(exactly = 1) { workFactory.entityFromModel(simpleWork()) }
        verify(exactly = 1) { workFactory.modelFromEntity(simpleWorkEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception create a Text`() {
        every { workRepository.save(simpleWorkEntity()) } throws Exception()
        val result = workService.create(simpleWork())
        result.shouldBeLeft()
    }

    @Test
    fun `update a Text`() {
        val result = workService.update(simpleWork().id!!, simpleWork())
        verify(exactly = 1) { workFactory.entityFromModel(simpleWork()) }
        verify(exactly = 1) { workFactory.modelFromEntity(simpleWorkEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception update a Text`() {
        every { workRepository.findById(simpleWork().id!!) } throws Exception()
        val result = workService.update(simpleWork().id!!, simpleWork())
        result.shouldBeLeft()
    }

    @Test
    fun `delete all Texts`() {
        val result = workService.deleteAll()
        verify(exactly = 1) { workRepository.deleteAll() }
        result.right()
    }

    @Test
    fun `delete a Text by id`() {
        val result = workService.delete(simpleWork().id!!)
        verify(exactly = 1) { workRepository.deleteById(simpleWork().id!!) }
        result.right()
    }

}