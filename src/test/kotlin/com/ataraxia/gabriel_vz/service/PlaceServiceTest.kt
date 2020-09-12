package com.ataraxia.gabriel_vz.service

import arrow.core.right
import com.ataraxia.gabriel_vz.factory.PlaceFactory
import com.ataraxia.gabriel_vz.model.Coordinates
import com.ataraxia.gabriel_vz.model.Place
import com.ataraxia.gabriel_vz.persistence.CoordinatesEntity
import com.ataraxia.gabriel_vz.persistence.PlaceEntity
import com.ataraxia.gabriel_vz.repository.PlaceRepository
import io.kotlintest.assertions.arrow.either.shouldBeLeft
import io.kotlintest.assertions.arrow.either.shouldBeRight
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.util.*

@TestInstance(PER_CLASS)
internal class PlaceServiceTest {

    private val placeRepository: PlaceRepository = mockkClass(PlaceRepository::class)
    private val placeFactory: PlaceFactory = mockkClass(PlaceFactory::class)
    private val placeService: PlaceService = PlaceService(placeFactory, placeRepository)

    private fun simplePlace(): Place = Place(
            id = "",
            name = "Test",
            locality = "Test",
            country = "Test",
            coordinates = Coordinates(23.22, 123.22)
    )

    private fun simplePlaceEntity(): PlaceEntity = PlaceEntity(
            id = "",
            name = "Test",
            locality = "Test",
            country = "Test",
            coordinates = CoordinatesEntity(23.22, 123.22)
    )

    @BeforeEach
    fun setUp() {
        every { placeFactory.modelFromEntity(simplePlaceEntity()) } returns simplePlace()
        every { placeFactory.entityFromModel(simplePlace()) } returns simplePlaceEntity()
        every { placeRepository.findAll() } returns mutableListOf(simplePlaceEntity())
        every { placeRepository.findById(simplePlace().id!!) } returns Optional.of(simplePlaceEntity())
        every { placeRepository.save(simplePlaceEntity()) } returns simplePlaceEntity()
    }

    @AfterEach
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun `get all Places`() {
        val result = placeService.getAll()
        verify(exactly = 1) { placeRepository.findAll() }
        verify(exactly = 1) { placeFactory.modelFromEntity(simplePlaceEntity()) }
        confirmVerified(placeRepository)
        confirmVerified(placeFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get all Places`() {
        every { placeRepository.findAll() } throws Exception()
        val result = placeService.getAll()
        result.shouldBeLeft()
    }

    @Test
    fun `get a Place by Id`() {
        val result = placeService.get(simplePlace().id!!)
        verify(exactly = 1) { placeRepository.findById(simplePlace().id!!) }
        verify(exactly = 1) { placeFactory.modelFromEntity(simplePlaceEntity()) }
        confirmVerified(placeRepository)
        confirmVerified(placeFactory)
        result.shouldBeRight()
    }

    @Test
    fun `check Exception get a Place by Id`() {
        every { placeRepository.findById(simplePlace().id!!) } throws Exception()
        val result = placeService.get(simplePlace().id!!)
        result.shouldBeLeft()
    }

    @Test
    fun `create a Place`() {
        val result = placeService.create(simplePlace())
        verify(exactly = 1) { placeRepository.save(simplePlaceEntity()) }
        verify(exactly = 1) { placeFactory.entityFromModel(simplePlace()) }
        verify(exactly = 1) { placeFactory.modelFromEntity(simplePlaceEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception create a Place`() {
        every { placeRepository.save(simplePlaceEntity()) } throws Exception()
        val result = placeService.create(simplePlace())
        result.shouldBeLeft()
    }

    @Test
    fun `update a Place`() {
        val result = placeService.update(simplePlace().id!!, simplePlace())
        verify(exactly = 1) { placeFactory.entityFromModel(simplePlace()) }
        verify(exactly = 1) { placeFactory.modelFromEntity(simplePlaceEntity()) }
        result.shouldBeRight()
    }

    @Test
    fun `check Exception update a Place`() {
        every { placeRepository.findById(simplePlaceEntity().id!!) } throws Exception()
        val result = placeService.update(simplePlace().id!!, simplePlace())
        result.shouldBeLeft()
    }

    @Test
    fun `delete all Places`() {
        val result = placeService.deleteAll()
        verify(exactly = 1) { placeRepository.deleteAll() }
        result.right()
    }

    @Test
    fun `delete a Place by id`() {
        val result = placeService.delete(simplePlace().id!!)
        verify(exactly = 1) { placeRepository.deleteById(simplePlace().id!!) }
        result.right()
    }

}