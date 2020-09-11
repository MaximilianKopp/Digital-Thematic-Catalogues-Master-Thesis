package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Coordinates
import com.ataraxia.gabriel_vz.model.Place
import com.ataraxia.gabriel_vz.persistence.CoordinatesEntity
import com.ataraxia.gabriel_vz.persistence.PlaceEntity
import com.ataraxia.gabriel_vz.resource.CoordinatesResource
import com.ataraxia.gabriel_vz.resource.PlaceResource
import io.kotlintest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@TestInstance(PER_CLASS)
internal class PlaceFactoryTest {

    private val coordinatesFactory = CoordinatesFactory()
    private val placeFactory = PlaceFactory(coordinatesFactory)

    private val placeUID = UUID.randomUUID().toString()

    private fun simplePlace() = Place(
            id = placeUID,
            locality = "Wiener Opernhaus",
            country = "Wien",
            name = "Beethovensaal",
            coordinates = Coordinates(
                    longitude = 44.23212,
                    latitude = 53.19827
            )
    )

    private fun simpleEntity() = PlaceEntity(
            id = placeUID,
            locality = "Wiener Opernhaus",
            country = "Wien",
            name = "Beethovensaal",
            coordinates = CoordinatesEntity(
                    longitude = 44.23212,
                    latitude = 53.19827
            )
    )

    private fun simplePlaceResource() = PlaceResource(
            self = null,
            id = placeUID,
            locality = "Wiener Opernhaus",
            country = "Wien",
            name = "Beethovensaal",
            coordinates = CoordinatesResource(
                    longitude = 44.23212,
                    latitude = 53.19827
            )
    )

    @Test
    fun `placeModel from placeEntity`() {
        val placeEntity = simpleEntity()
        val place = placeFactory.modelFromEntity(placeEntity)
        val coordinates = coordinatesFactory.modelFromEntity(placeEntity.coordinates)

        assertNotNull(place)
        assertEquals(place.id, placeEntity.id)
        assertEquals(place.locality, placeEntity.locality)
        assertEquals(place.country, placeEntity.country)
        assertEquals(place.name, placeEntity.name)
        assertEquals(place.coordinates.longitude, coordinates.longitude)
        assertEquals(place.coordinates.latitude, coordinates.latitude)
        place.shouldBeInstanceOf<Place>()
    }

    @Test
    fun `placeEntity from placeModel `() {
        val place = simplePlace()
        val placeEntity = placeFactory.entityFromModel(place)
        val coordinatesEntity = coordinatesFactory.entityFromModel(place.coordinates)

        assertNotNull(placeEntity)
        assertEquals(placeEntity.id, place.id)
        assertEquals(placeEntity.locality, place.locality)
        assertEquals(placeEntity.country, place.country)
        assertEquals(placeEntity.name, place.name)
        assertEquals(placeEntity.coordinates.longitude, coordinatesEntity.longitude)
        assertEquals(placeEntity.coordinates.latitude, coordinatesEntity.latitude)
        place.shouldBeInstanceOf<Place>()
    }

    @Test
    fun `placeModel from placeResource `() {
        val placeResource = simplePlaceResource()
        val place = placeFactory.modelFromResource(placeResource)
        val coordinates = coordinatesFactory.modelFromResource(placeResource.coordinates)

        assertNotNull(place)
        assertEquals(place.id, placeResource.id)
        assertEquals(place.locality, placeResource.locality)
        assertEquals(place.country, placeResource.country)
        assertEquals(place.name, placeResource.name)
        assertEquals(place.coordinates.longitude, coordinates.longitude)
        assertEquals(place.coordinates.latitude, coordinates.latitude)
        place.shouldBeInstanceOf<Place>()
    }

    @Test
    fun `placeResource from place `() {
        val place = simplePlace()
        val placeResource = placeFactory.resourceFromModel(place)
        val coordinates = coordinatesFactory.resourceFromModel(place.coordinates)

        assertNotNull(place)
        assertEquals(placeResource.id, place.id)
        assertEquals(placeResource.locality, place.locality)
        assertEquals(placeResource.country, place.country)
        assertEquals(placeResource.name, place.name)
        assertEquals(placeResource.coordinates.longitude, coordinates.longitude)
        assertEquals(placeResource.coordinates.latitude, coordinates.latitude)
        place.shouldBeInstanceOf<Place>()
    }


}