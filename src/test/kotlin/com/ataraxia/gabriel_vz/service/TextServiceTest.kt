package com.ataraxia.gabriel_vz.service

import arrow.core.right
import com.ataraxia.gabriel_vz.factory.TextFactory
import com.ataraxia.gabriel_vz.model.Text
import com.ataraxia.gabriel_vz.persistence.TextEntity
import com.ataraxia.gabriel_vz.repository.TextRepository
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
internal class TextServiceTest {

    private val textFactory: TextFactory = mockkClass(TextFactory::class)
    private val textRepository: TextRepository = mockkClass(TextRepository::class)
    private val textService: TextService = TextService(textFactory, textRepository)

    private fun simpleText() = Text(
            id = "",
            created = OffsetDateTime.now(),
            modified = null,
            author = "James Taylor",
            title = "The nine cats",
            excerpt = "Nine tiny little cats..."
    )

    private fun simpleTextEntity() = TextEntity(
            id = "",
            created = OffsetDateTime.now(),
            modified = null,
            author = "James Taylor",
            title = "The nine cats",
            excerpt = "Nine tiny little cats..."
    )

    @BeforeEach
    fun setUp() {
        every { textFactory.modelFromEntity(simpleTextEntity()) } returns simpleText()
        every { textFactory.entityFromModel(simpleText()) } returns simpleTextEntity()
        every { textRepository.findAll() } returns mutableListOf(simpleTextEntity())
        every { textRepository.findById(simpleText().id!!) } returns Optional.of(simpleTextEntity())
        every { textRepository.save(simpleTextEntity()) } returns simpleTextEntity()
    }

    @AfterEach
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun `get all Texts`() {
        val result = textService.getAll()
        verify(exactly = 1) { textRepository.findAll() }
        verify(exactly = 1) { textFactory.modelFromEntity(simpleTextEntity()) }
        confirmVerified(textRepository)
        confirmVerified(textFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get all Texts`() {
        every { textRepository.findAll() } throws Exception()
        val result = textService.getAll()
        result.shouldBeLeft()
    }

    @Test
    fun `get a Text by Id`() {
        val result = textService.get(simpleText().id!!)
        verify(exactly = 1) { textRepository.findById(simpleText().id!!) }
        verify(exactly = 1) { textFactory.modelFromEntity(simpleTextEntity()) }
        confirmVerified(textRepository)
        confirmVerified(textFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get a Text by Id`() {
        every { textRepository.findById(simpleText().id!!) } throws Exception()
        val result = textService.get(simpleText().id!!)
        result.shouldBeLeft()
    }

    @Test
    fun `create a Text`() {
        val result = textService.create(simpleText())
        verify(exactly = 1) { textRepository.save(simpleTextEntity()) }
        verify(exactly = 1) { textFactory.entityFromModel(simpleText()) }
        verify(exactly = 1) { textFactory.modelFromEntity(simpleTextEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception create a Text`() {
        every { textRepository.save(simpleTextEntity()) } throws Exception()
        val result = textService.create(simpleText())
        result.shouldBeLeft()
    }

    @Test
    fun `update a Text`() {
        val result = textService.update(simpleText().id!!, simpleText())
        verify(exactly = 1) { textFactory.entityFromModel(simpleText()) }
        verify(exactly = 1) { textFactory.modelFromEntity(simpleTextEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception update a Text`() {
        every { textRepository.findById(simpleText().id!!) } throws Exception()
        val result = textService.update(simpleText().id!!, simpleText())
        result.shouldBeLeft()
    }

    @Test
    fun `delete all Texts`() {
        val result = textService.deleteAll()
        verify(exactly = 1) { textRepository.deleteAll() }
        result.right()
    }

    @Test
    fun `delete a Text by id`() {
        val result = textService.delete(simpleText().id!!)
        verify(exactly = 1) { textRepository.deleteById(simpleText().id!!) }
        result.right()
    }
}