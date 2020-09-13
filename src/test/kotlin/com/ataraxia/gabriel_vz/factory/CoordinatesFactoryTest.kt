package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Coordinates
import com.ataraxia.gabriel_vz.persistence.CoordinatesEntity
import com.ataraxia.gabriel_vz.resource.CoordinatesResource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@TestInstance(PER_CLASS)
internal class CoordinatesFactoryTest {
    private val coordinatesFactory = CoordinatesFactory()
    private fun simpleCoordinates() = Coordinates(
            longitude = 23.23232,
            latitude = 13.2322
    )

    private fun simpleCoordinatesEntity() = CoordinatesEntity(
            longitude = 23.23232,
            latitude = 13.2322
    )

    private fun simpleCoordinatesResource() = CoordinatesResource(
            longitude = 23.23232,
            latitude = 13.2322
    )

    @Test
    fun `coordinates from coordinatesEntity`() {
        val coordinatesEntity = simpleCoordinatesEntity()
        val coordinates = coordinatesFactory.modelFromEntity(coordinatesEntity)
        assertNotNull(coordinates)
        assertEquals(coordinates.longitude, coordinatesEntity.longitude)
        assertEquals(coordinates.latitude, coordinatesEntity.latitude)
        coordinates.shouldBeInstanceOf<Coordinates>()
    }

    @Test
    fun `coordinatesEntity from coordinatesModel`() {
        val coordinates = simpleCoordinates()
        val coordinatesEntity = coordinatesFactory.entityFromModel(coordinates)
        assertNotNull(coordinatesEntity)
        assertEquals(coordinatesEntity.longitude, coordinates.longitude)
        assertEquals(coordinatesEntity.latitude, coordinates.latitude)
        coordinatesEntity.shouldBeInstanceOf<CoordinatesEntity>()
    }

    @Test
    fun `coordinates from coordinatesResource`() {
        val coordinatesResource = simpleCoordinatesResource()
        val coordinates = coordinatesFactory.modelFromResource(coordinatesResource)
        assertNotNull(coordinates)
        assertEquals(coordinates.longitude, coordinatesResource.longitude)
        assertEquals(coordinates.latitude, coordinatesResource.latitude)
        coordinates.shouldBeInstanceOf<Coordinates>()
    }

    @Test
    fun `coordinatesResource from coordinates`() {
        val coordinates = simpleCoordinates()
        val coordinatesResource = coordinatesFactory.resourceFromModel(coordinates)
        assertNotNull(coordinates)
        assertEquals(coordinatesResource.longitude, coordinates.longitude)
        assertEquals(coordinatesResource.latitude, coordinates.latitude)
        coordinatesResource.shouldBeInstanceOf<CoordinatesResource>()
    }

}