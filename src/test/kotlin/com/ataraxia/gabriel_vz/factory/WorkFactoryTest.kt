package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.persistence.WorkEntity
import com.ataraxia.gabriel_vz.resource.WorkResource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@TestInstance(PER_CLASS)
internal class WorkFactoryTest {

    private val workID = UUID.randomUUID().toString()
    private var personFactory = PersonFactory()
    private var literatureFactory = LiteratureFactory()
    private var discographyFactory = DiscographyFactory(personFactory)
    private var workFactory = mockk<WorkFactory>()

    private fun simpleWork() = Work(
            id = workID,
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
            id = workID,
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

    private fun simpleWorkResource() = WorkResource(
            self = null,
            id = workID,
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
    fun setup() {
        workFactory = WorkFactory(discographyFactory, literatureFactory, personFactory)
    }

    @Test
    fun `workModel from workEntity`() {
        val workEntity = simpleWorkEntity()
        val work = workFactory.modelFromEntity(workEntity)

        assertNotNull(work)
        assertEquals(work.id, workEntity.id)
        assertEquals(work.title, workEntity.title)
        assertEquals(work.category, workEntity.category)
        assertEquals(work.commentary, workEntity.commentary)
        assertEquals(work.dateOfCreation, workEntity.dateOfCreation)
        assertEquals(work.dateOfPremiere, workEntity.dateOfPremiere)
        assertEquals(work.dedication, workEntity.dedication)
        assertEquals(work.duration, workEntity.duration)
        assertEquals(work.editor, workEntity.editor)
        assertEquals(work.instrumentation, workEntity.instrumentation)
        work.shouldBeInstanceOf<Work>()
    }

    @Test
    fun `workEntity from workModel`() {
        val work = simpleWork()
        val workEntity = workFactory.entityFromModel(work)

        assertNotNull(workEntity)
        assertEquals(workEntity.id, work.id)
        assertEquals(workEntity.title, work.title)
        assertEquals(workEntity.category, work.category)
        assertEquals(workEntity.commentary, work.commentary)
        assertEquals(workEntity.dateOfCreation, work.dateOfCreation)
        assertEquals(workEntity.dateOfPremiere, work.dateOfPremiere)
        assertEquals(workEntity.dedication, work.dedication)
        assertEquals(workEntity.duration, work.duration)
        assertEquals(workEntity.editor, work.editor)
        assertEquals(workEntity.instrumentation, work.instrumentation)
        workEntity.shouldBeInstanceOf<WorkEntity>()
    }

    @Test
    fun `workModel from workResource`() {
        val workResource = simpleWorkResource()
        val work = workFactory.modelFromResource(workResource)

        assertNotNull(work)
        assertEquals(work.id, workResource.id)
        assertEquals(work.title, workResource.title)
        assertEquals(work.category, workResource.category)
        assertEquals(work.commentary, workResource.commentary)
        assertEquals(work.dateOfCreation, workResource.dateOfCreation)
        assertEquals(work.dateOfPremiere, workResource.dateOfPremiere)
        assertEquals(work.dedication, workResource.dedication)
        assertEquals(work.duration, workResource.duration)
        assertEquals(work.editor, workResource.editor)
        assertEquals(work.instrumentation, workResource.instrumentation)
        work.shouldBeInstanceOf<Work>()
    }

    @Test
    fun `workResource from workModel`() {
        val work = simpleWork()
        val workResource = workFactory.resourceFromModel(work)

        assertNotNull(workResource)
        assertEquals(workResource.id, work.id)
        assertEquals(workResource.title, work.title)
        assertEquals(workResource.category, work.category)
        assertEquals(workResource.commentary, work.commentary)
        assertEquals(workResource.dateOfCreation, work.dateOfCreation)
        assertEquals(workResource.dateOfPremiere, work.dateOfPremiere)
        assertEquals(workResource.dedication, work.dedication)
        assertEquals(workResource.duration, work.duration)
        assertEquals(workResource.editor, work.editor)
        assertEquals(workResource.instrumentation, work.instrumentation)
        workResource.shouldBeInstanceOf<WorkResource>()
    }

}