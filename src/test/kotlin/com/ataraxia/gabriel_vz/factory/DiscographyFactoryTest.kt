//package com.ataraxia.gabriel_vz.factory
//
//import com.ataraxia.gabriel_vz.model.Discography
//import com.ataraxia.gabriel_vz.persistence.DiscographyEntity
//import com.ataraxia.gabriel_vz.resource.DiscographyResource
//import io.kotlintest.matchers.types.shouldBeInstanceOf
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Assertions.assertNotNull
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.TestInstance
//import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
//import org.mockito.Mock
//import java.util.*
//
//@TestInstance(PER_CLASS)
//
//internal class DiscographyFactoryTest {
//
//    private val discographyID = UUID.randomUUID().toString()
//    private val personFactory = PersonFactory()
//    private val discographyFactory: DiscographyFactory = DiscographyFactory(personFactory)
//
//    private fun simpleDiscography() = Discography(
//            id = discographyID,
//            title = "Wolfgang Gabriel - all Sonatas",
//            musicians = mutableSetOf(),
//            recordId = "1928-232",
//            label = "Deutsche Gramophon",
//            dateOfPublishing = "12.02.2018"
//    )
//
//    private fun simpleDiscographyEntity() = DiscographyEntity(
//            id = discographyID,
//            title = "Wolfgang Gabriel - all Sonatas",
//            musicians = mutableSetOf(),
//            recordId = "1928-232",
//            label = "Deutsche Gramophon",
//            dateOfPublishing = "12.02.2018"
//    )
//
//    private fun simpleDiscographyResource() = DiscographyResource(
//            self = null,
//            id = discographyID,
//            title = "Wolfgang Gabriel - all Sonatas",
//            musicians = mutableSetOf(),
//            recordId = "1928-232",
//            label = "Deutsche Gramophon",
//            dateOfPublishing = "12.02.2018"
//    )
//
//    @Test
//    fun `discographyModel from discographyEntity`() {
//        val discographyEntity = simpleDiscographyEntity()
//        val discography = discographyFactory.modelFromEntity(discographyEntity)
//
//        assertNotNull(discography)
//        assertEquals(discography.id, discographyEntity.id)
//        assertEquals(discography.title, discographyEntity.title)
//        assertEquals(discography.recordId, discographyEntity.recordId)
//        assertEquals(discography.label, discographyEntity.label)
//        assertEquals(discography.dateOfPublishing, discographyEntity.dateOfPublishing)
//        discography.shouldBeInstanceOf<Discography>()
//    }
//
//    @Test
//    fun `discographyEntity from discographyModel`() {
//        val discography = simpleDiscography()
//        val discographyEntity = discographyFactory.entityFromModel(discography)
//
//        assertNotNull(discographyEntity)
//        assertEquals(discographyEntity.id, discography.id)
//        assertEquals(discographyEntity.title, discography.title)
//        assertEquals(discographyEntity.recordId, discography.recordId)
//        assertEquals(discographyEntity.label, discography.label)
//        assertEquals(discographyEntity.dateOfPublishing, discography.dateOfPublishing)
//        discographyEntity.shouldBeInstanceOf<DiscographyEntity>()
//    }
//
//    @Test
//    fun `discographyModel from discographyResource`() {
//        val discographyResource = simpleDiscographyResource()
//        val discography = discographyFactory.modelFromResource(discographyResource)
//
//        assertNotNull(discography)
//        assertEquals(discography.id, discographyResource.id)
//        assertEquals(discography.title, discographyResource.title)
//        assertEquals(discography.recordId, discographyResource.recordId)
//        assertEquals(discography.label, discographyResource.label)
//        assertEquals(discography.dateOfPublishing, discographyResource.dateOfPublishing)
//        discography.shouldBeInstanceOf<Discography>()
//    }
//
//    @Test
//    fun `discographyResource from discographyModel`() {
//        val discography = simpleDiscography()
//        val discographyResource = discographyFactory.resourceFromModel(discography)
//
//        assertNotNull(discographyResource)
//        assertEquals(discographyResource.id, discography.id)
//        assertEquals(discographyResource.title, discography.title)
//        assertEquals(discographyResource.recordId, discography.recordId)
//        assertEquals(discographyResource.label, discography.label)
//        assertEquals(discographyResource.dateOfPublishing, discography.dateOfPublishing)
//        discographyResource.shouldBeInstanceOf<DiscographyResource>()
//    }
//}