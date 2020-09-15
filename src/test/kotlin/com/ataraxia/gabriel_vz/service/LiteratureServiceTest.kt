package com.ataraxia.gabriel_vz.service

import arrow.core.right
import com.ataraxia.gabriel_vz.factory.LiteratureFactory
import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.persistence.LiteratureEntity
import com.ataraxia.gabriel_vz.repository.LiteratureRepository
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
internal class LiteratureServiceTest {

    private val literatureFactory: LiteratureFactory = mockkClass(LiteratureFactory::class)
    private val literatureRepository: LiteratureRepository = mockkClass(LiteratureRepository::class)
    private val literatureService: LiteratureService = LiteratureService(literatureFactory, literatureRepository)

    private fun simpleLiterature() = Literature(
            id = "",
            created = OffsetDateTime.now(),
            modified = null,
            title = "Literature",
            author = "Ulrike Schneider",
            isbn = "9312342321237",
            yearOfPublishing = "2014"
    )

    private fun simpleLiteratureEntity() = LiteratureEntity(
            id = "",
            created = OffsetDateTime.now(),
            modified = null,
            title = "Literature",
            author = "Ulrike Schneider",
            isbn = "9312342321237",
            yearOfPublishing = "2014"
    )

    @BeforeEach
    fun setUp() {
        every { literatureFactory.modelFromEntity(simpleLiteratureEntity()) } returns simpleLiterature()
        every { literatureFactory.entityFromModel(simpleLiterature()) } returns simpleLiteratureEntity()
        every { literatureRepository.findAll() } returns mutableListOf(simpleLiteratureEntity())
        every { literatureRepository.findById(simpleLiterature().id!!) } returns Optional.of(simpleLiteratureEntity())
        every { literatureRepository.save(simpleLiteratureEntity()) } returns simpleLiteratureEntity()
    }

    @AfterEach
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun `get all Texts`() {
        val result = literatureService.getAll()
        verify(exactly = 1) { literatureRepository.findAll() }
        verify(exactly = 1) { literatureFactory.modelFromEntity(simpleLiteratureEntity()) }
        confirmVerified(literatureRepository)
        confirmVerified(literatureFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get all Texts`() {
        every { literatureRepository.findAll() } throws Exception()
        val result = literatureService.getAll()
        result.shouldBeLeft()
    }

    @Test
    fun `get a Text by Id`() {
        val result = literatureService.get(simpleLiterature().id!!)
        verify(exactly = 1) { literatureRepository.findById(simpleLiterature().id!!) }
        verify(exactly = 1) { literatureFactory.modelFromEntity(simpleLiteratureEntity()) }
        confirmVerified(literatureRepository)
        confirmVerified(literatureFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get a Text by Id`() {
        every { literatureRepository.findById(simpleLiterature().id!!) } throws Exception()
        val result = literatureService.get(simpleLiterature().id!!)
        result.shouldBeLeft()
    }

    @Test
    fun `create a Text`() {
        val result = literatureService.create(simpleLiterature())
        verify(exactly = 1) { literatureRepository.save(simpleLiteratureEntity()) }
        verify(exactly = 1) { literatureFactory.entityFromModel(simpleLiterature()) }
        verify(exactly = 1) { literatureFactory.modelFromEntity(simpleLiteratureEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception create a Text`() {
        every { literatureRepository.save(simpleLiteratureEntity()) } throws Exception()
        val result = literatureService.create(simpleLiterature())
        result.shouldBeLeft()
    }

    @Test
    fun `update a Text`() {
        val result = literatureService.update(simpleLiterature().id!!, simpleLiterature())
        verify(exactly = 1) { literatureFactory.entityFromModel(simpleLiterature()) }
        verify(exactly = 1) { literatureFactory.modelFromEntity(simpleLiteratureEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception update a Text`() {
        every { literatureRepository.findById(simpleLiterature().id!!) } throws Exception()
        val result = literatureService.update(simpleLiterature().id!!, simpleLiterature())
        result.shouldBeLeft()
    }

    @Test
    fun `delete all Texts`() {
        val result = literatureService.deleteAll()
        verify(exactly = 1) { literatureRepository.deleteAll() }
        result.right()
    }

    @Test
    fun `delete a Text by id`() {
        val result = literatureService.delete(simpleLiterature().id!!)
        verify(exactly = 1) { literatureRepository.deleteById(simpleLiterature().id!!) }
        result.right()
    }

}