package com.ataraxia.gabriel_vz.service

import arrow.core.right
import com.ataraxia.gabriel_vz.factory.DiscographyFactory
import com.ataraxia.gabriel_vz.model.Discography
import com.ataraxia.gabriel_vz.persistence.DiscographyEntity
import com.ataraxia.gabriel_vz.repository.DiscographyRepository
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
internal class DiscographyServiceTest {

    private val discographyFactory: DiscographyFactory = mockkClass(DiscographyFactory::class)
    private val discographyRepository: DiscographyRepository = mockkClass(DiscographyRepository::class)
    private val discographyService: DiscographyService = DiscographyService(discographyFactory, discographyRepository)

    private fun simpleDiscography() = Discography(
            id = "",
            created = OffsetDateTime.now(),
            modified = null,
            title = "Wolfgang Gabriel - all Sonatas",
            musicians = mutableSetOf(),
            recordId = "1928-232",
            label = "Deutsche Gramophon",
            dateOfPublishing = "12.02.2018"
    )

    private fun simpleDiscographyEntity() = DiscographyEntity(
            id = "",
            created = OffsetDateTime.now(),
            modified = null,
            title = "Wolfgang Gabriel - all Sonatas",
            musicians = mutableSetOf(),
            recordId = "1928-232",
            label = "Deutsche Gramophon",
            dateOfPublishing = "12.02.2018"
    )

    @BeforeEach
    fun setUp() {
        every { discographyFactory.modelFromEntity(simpleDiscographyEntity()) } returns simpleDiscography()
        every { discographyFactory.entityFromModel(simpleDiscography()) } returns simpleDiscographyEntity()
        every { discographyRepository.findAll() } returns mutableListOf(simpleDiscographyEntity())
        every { discographyRepository.findById(simpleDiscography().id!!) } returns Optional.of(simpleDiscographyEntity())
        every { discographyRepository.save(simpleDiscographyEntity()) } returns simpleDiscographyEntity()
    }

    @AfterEach
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun `get all Incipits`() {
        val result = discographyService.getAll()
        verify(exactly = 1) { discographyRepository.findAll() }
        verify(exactly = 1) { discographyFactory.modelFromEntity(simpleDiscographyEntity()) }
        confirmVerified(discographyRepository)
        confirmVerified(discographyRepository)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get all Incipits`() {
        every { discographyRepository.findAll() } throws Exception()
        val result = discographyService.getAll()
        result.shouldBeLeft()
    }

    @Test
    fun `get Incipit by Id`() {
        val result = discographyService.get(simpleDiscography().id!!)
        verify(exactly = 1) { discographyRepository.findById(simpleDiscography().id!!) }
        verify(exactly = 1) { discographyFactory.modelFromEntity(simpleDiscographyEntity()) }
        confirmVerified(discographyRepository)
        confirmVerified(discographyFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get Incipit by Id`() {
        every { discographyRepository.findById(simpleDiscography().id!!) } throws Exception()
        val result = discographyService.get(simpleDiscography().id!!)
        result.shouldBeLeft()
    }

    @Test
    fun `create an Incipit`() {
        val result = discographyService.create(simpleDiscography())
        verify(exactly = 1) { discographyRepository.save(simpleDiscographyEntity()) }
        verify(exactly = 1) { discographyFactory.entityFromModel(simpleDiscography()) }
        verify(exactly = 1) { discographyFactory.modelFromEntity(simpleDiscographyEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception create an Incipit`() {
        every { discographyRepository.save(simpleDiscographyEntity()) } throws Exception()
        val result = discographyService.create(simpleDiscography())
        result.shouldBeLeft()
    }

    @Test
    fun `update an Incipit`() {
        val result = discographyService.update(simpleDiscography().id!!, simpleDiscography())
        verify(exactly = 1) { discographyFactory.entityFromModel(simpleDiscography()) }
        verify(exactly = 1) { discographyFactory.modelFromEntity(simpleDiscographyEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception update Incipit`() {
        every { discographyRepository.findById(simpleDiscography().id!!) } throws Exception()
        val result = discographyService.update(simpleDiscography().id!!, simpleDiscography())
        result.shouldBeLeft()
    }

    @Test
    fun `delete all Incipits`() {
        val result = discographyService.deleteAll()
        verify(exactly = 1) { discographyRepository.deleteAll() }
        result.right()
    }

    @Test
    fun `delete an Incipit by id`() {
        val result = discographyService.delete(simpleDiscography().id!!)
        verify(exactly = 1) { discographyRepository.deleteById(simpleDiscography().id!!) }
        result.right()
    }

}