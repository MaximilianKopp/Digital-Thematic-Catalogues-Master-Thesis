package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Incipit
import com.ataraxia.gabriel_vz.persistence.IncipitEntity
import com.ataraxia.gabriel_vz.resource.IncipitResource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.time.OffsetDateTime
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@TestInstance(PER_CLASS)
internal class IncipitFactoryTest {
    private val incipitFactory = IncipitFactory()
    private val incipitID = UUID.randomUUID().toString()

    private fun simpleIncipit() = Incipit(
            id = incipitID,
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
            id = incipitID,
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

    private fun simpleIncipitResource() = IncipitResource(
            self = null,
            collection = null,
            id = incipitID,
            created = OffsetDateTime.now().toString(),
            modified = null,
            title = "Incipit",
            text = "Flowers in the field",
            description = "Text aus Gabriels Liederkreis",
            clef = "Violin",
            score = "f-a-g",
            timesig = "2/4",
            keysig = "g"
    )

    @Test
    fun `incipitModel from incipitEntity`() {
        val incipitEntity = simpleIncipitEntity()
        val incipit = incipitFactory.modelFromEntity(incipitEntity)

        assertNotNull(incipit)
        assertEquals(incipit.id, incipitEntity.id)
        assertEquals(incipit.text, incipitEntity.text)
        assertEquals(incipit.description, incipitEntity.description)
        assertEquals(incipit.score, incipitEntity.score)
        assertEquals(incipit.timesig, incipitEntity.timesig)
        assertEquals(incipit.keysig, incipitEntity.keysig)
        incipit.shouldBeInstanceOf<Incipit>()
    }

    @Test
    fun `incipitEntity from incipitModel`() {
        val incipit = simpleIncipit()
        val incipitEntity = incipitFactory.entityFromModel(incipit)

        assertNotNull(incipitEntity)
        assertEquals(incipitEntity.id, incipit.id)
        assertEquals(incipitEntity.text, incipit.text)
        assertEquals(incipitEntity.description, incipit.description)
        assertEquals(incipitEntity.score, incipit.score)
        assertEquals(incipitEntity.timesig, incipit.timesig)
        assertEquals(incipitEntity.keysig, incipit.keysig)
        incipitEntity.shouldBeInstanceOf<IncipitEntity>()
    }

    @Test
    fun `incipitModel from incipitResource`() {
        val incipitResource = simpleIncipitResource()
        val incipit = incipitFactory.modelFromResource(incipitResource)

        assertNotNull(incipit)
        assertEquals(incipit.id, incipitResource.id)
        assertEquals(incipit.text, incipitResource.text)
        assertEquals(incipit.description, incipitResource.description)
        assertEquals(incipit.score, incipitResource.score)
        assertEquals(incipit.timesig, incipitResource.timesig)
        assertEquals(incipit.keysig, incipitResource.keysig)
        incipit.shouldBeInstanceOf<Incipit>()
    }

    @Test
    fun `incipitResource from incipitModel`() {
        val incipit = simpleIncipit()
        val incipitResource = incipitFactory.resourceFromModel(incipit)

        assertNotNull(incipitResource)
        assertEquals(incipitResource.id, incipit.id)
        assertEquals(incipitResource.text, incipit.text)
        assertEquals(incipitResource.description, incipit.description)
        assertEquals(incipitResource.score, incipit.score)
        assertEquals(incipitResource.timesig, incipit.timesig)
        assertEquals(incipitResource.keysig, incipit.keysig)
        incipitResource.shouldBeInstanceOf<IncipitResource>()
    }
}