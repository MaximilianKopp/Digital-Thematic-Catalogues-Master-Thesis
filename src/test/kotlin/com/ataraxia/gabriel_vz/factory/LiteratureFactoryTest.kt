package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.persistence.LiteratureEntity
import com.ataraxia.gabriel_vz.resource.LiteratureResource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.time.OffsetDateTime
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@TestInstance(PER_CLASS)
internal class LiteratureFactoryTest {

    private val literatureFactory = LiteratureFactory()
    private val literatureID = UUID.randomUUID().toString()

    private fun simpleLiterature() = Literature(
            id = literatureID,
            created = OffsetDateTime.now(),
            modified = OffsetDateTime.now(),
            title = "Literature",
            author = "Ulrike Schneider",
            isbn = "9312342321237",
            yearOfPublishing = "2014"
    )

    private fun simpleLiteratureEntity() = LiteratureEntity(
            id = literatureID,
            created = OffsetDateTime.now(),
            modified = OffsetDateTime.now(),
            title = "Literature",
            author = "Ulrike Schneider",
            isbn = "9312342321237",
            yearOfPublishing = "2014",
            relatedWorks = mutableSetOf()
    )

    private fun simpleLiteratureResource() = LiteratureResource(
            self = null,
            collection = null,
            id = literatureID,
            created = OffsetDateTime.now().toString(),
            modified = null,
            title = "Literature",
            author = "Ulrike Schneider",
            isbn = "9312342321237",
            yearOfPublishing = "2014"
    )

    @Test
    fun `literatureModel from literatureEntity`() {
        val literatureEntity = simpleLiteratureEntity()
        val literature = literatureFactory.modelFromEntity(literatureEntity)

        assertNotNull(literature)
        assertEquals(literature.id, literatureEntity.id)
        assertEquals(literature.author, literatureEntity.author)
        assertEquals(literature.isbn, literatureEntity.isbn)
        assertEquals(literature.yearOfPublishing, literatureEntity.yearOfPublishing)
        literature.shouldBeInstanceOf<Literature>()
    }

    @Test
    fun `literatureEntity from literatureModel`() {
        val literature = simpleLiterature()
        val literatureEntity = literatureFactory.entityFromModel(literature)

        assertNotNull(literatureEntity)
        assertEquals(literatureEntity.id, literature.id)
        assertEquals(literatureEntity.author, literature.author)
        assertEquals(literatureEntity.isbn, literature.isbn)
        assertEquals(literatureEntity.yearOfPublishing, literature.yearOfPublishing)
        literatureEntity.shouldBeInstanceOf<LiteratureEntity>()
    }

    @Test
    fun `literatureModel from literatureResource`() {
        val literatureResource = simpleLiteratureResource()
        val literature = literatureFactory.modelFromResource(literatureResource)

        assertNotNull(literature)
        assertEquals(literature.id, literatureResource.id)
        assertEquals(literature.author, literatureResource.author)
        assertEquals(literature.isbn, literatureResource.isbn)
        assertEquals(literature.yearOfPublishing, literatureResource.yearOfPublishing)
        literature.shouldBeInstanceOf<Literature>()
    }

    @Test
    fun `literatureResource from literatureModel`() {
        val literature = simpleLiterature()
        val literatureResource = literatureFactory.resourceFromModel(literature)

        assertNotNull(literatureResource)
        assertEquals(literatureResource.id, literature.id)
        assertEquals(literatureResource.author, literature.author)
        assertEquals(literatureResource.isbn, literature.isbn)
        assertEquals(literatureResource.yearOfPublishing, literature.yearOfPublishing)
        literatureResource.shouldBeInstanceOf<LiteratureResource>()
    }
}