package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Text
import com.ataraxia.gabriel_vz.persistence.TextEntity
import com.ataraxia.gabriel_vz.resource.TextResource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.time.OffsetDateTime
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@TestInstance(PER_CLASS)
internal class TextFactoryTest {

    private val textID = UUID.randomUUID().toString()
    private val textFactory = TextFactory()

    private fun simpleText() = Text(
            id = textID,
            created = OffsetDateTime.now(),
            modified = null,
            author = "James Taylor",
            title = "The nine cats",
            excerpt = "Nine tiny little cats..."
    )

    private fun simpleTextEntity() = TextEntity(
            id = textID,
            created = OffsetDateTime.now(),
            modified = null,
            author = "James Taylor",
            title = "The nine cats",
            excerpt = "Nine tiny little cats..."
    )

    private fun simpleTextResource() = TextResource(
            self = null,
            created = OffsetDateTime.now().toString(),
            modified = null,
            id = textID,
            author = "James Taylor",
            title = "The nine cats",
            excerpt = "Nine tiny little cats..."
    )

    @Test
    fun `textModel from textEntity`() {
        val textEntity = simpleTextEntity()
        val text = textFactory.modelFromEntity(textEntity)

        assertNotNull(text)
        assertEquals(text.id, textEntity.id)
        assertEquals(text.author, textEntity.author)
        assertEquals(text.title, textEntity.title)
        assertEquals(text.excerpt, textEntity.excerpt)
        text.shouldBeInstanceOf<Text>()
    }

    @Test
    fun `textEntity from textModel`() {
        val text = simpleText()
        val textEntity = textFactory.entityFromModel(text)

        assertNotNull(textEntity)
        assertEquals(textEntity.id, text.id)
        assertEquals(textEntity.author, text.author)
        assertEquals(textEntity.title, text.title)
        assertEquals(textEntity.excerpt, text.excerpt)
        textEntity.shouldBeInstanceOf<TextEntity>()
    }

    @Test
    fun `textModel from textResource`() {
        val textResource = simpleTextResource()
        val text = textFactory.modelFromResource(textResource)

        assertNotNull(text)
        assertEquals(text.id, textResource.id)
        assertEquals(text.author, textResource.author)
        assertEquals(text.title, textResource.title)
        assertEquals(text.excerpt, textResource.excerpt)
        text.shouldBeInstanceOf<Text>()
    }

    @Test
    fun `textResource from textModel`() {
        val text = simpleText()
        val textResource = textFactory.resourceFromModel(text)

        assertNotNull(textResource)
        assertEquals(textResource.id, text.id)
        assertEquals(textResource.author, text.author)
        assertEquals(textResource.title, text.title)
        assertEquals(textResource.excerpt, text.excerpt)
        textResource.shouldBeInstanceOf<TextResource>()
    }
}