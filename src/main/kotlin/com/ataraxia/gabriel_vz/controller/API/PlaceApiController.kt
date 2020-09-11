package com.ataraxia.gabriel_vz.controller.API

import arrow.core.Either
import com.ataraxia.gabriel_vz.errorhandling.ResourceNotFoundException
import com.ataraxia.gabriel_vz.factory.PlaceFactory
import com.ataraxia.gabriel_vz.model.Place
import com.ataraxia.gabriel_vz.resource.PlaceResource
import com.ataraxia.gabriel_vz.root.Controller
import com.ataraxia.gabriel_vz.service.PlaceService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(value = "/places", description = "Operations about Places")
@RequestMapping("/api")
class PlaceApiController(
        private val placeFactory: PlaceFactory,
        private val placeService: PlaceService
) : Controller<Place>() {

    @GetMapping("/places")
    @ApiOperation(
            value = "Find all Places",
            notes = "Provides a list of all Places",
            response = PlaceResource::class
    )
    override fun all(): ResponseEntity<Any> {
        val result: Either<Exception, List<PlaceResource>> = placeService.getAll()
                .map { it.map(placeFactory::resourceFromModel) }
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 No Place found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @GetMapping("/places/{id}")
    @ApiOperation(
            value = "Find a Place by id",
            notes = "Provides an id to look up a specific Place",
            response = PlaceResource::class
    )
    override fun one(
            @ApiParam(value = "ID value for the resource you need to retrieve")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result: Either<Exception, PlaceResource> = placeService.get(id)
                .map(placeFactory::resourceFromModel)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 Place '$id' not found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @PostMapping("/places", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(
            value = "Create a Place by id",
            notes = "Provides an id to create a specific Place",
            response = PlaceResource::class
    )
    override fun create(
            @ApiParam(value = "Model value for the resource you need to create")
            @RequestBody m: Place): ResponseEntity<Any> {
        val result = placeService.create(m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.CREATED) }
        )
    }

    @PutMapping("/places/{id}")
    @ApiOperation(
            value = "Update a Place by id",
            notes = "Provides an id to update a specific Place",
            response = PlaceResource::class
    )
    override fun update(
            @ApiParam(value = "ID value for the resource you need to retrieve")
            @PathVariable("id") id: String,
            @ApiParam(value = "Model value for the resource you need to update")
            @RequestBody m: Place): ResponseEntity<Any> {
        val result = placeService.update(id, m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(m, HttpStatus.ACCEPTED) }
        )
    }

    @DeleteMapping("/places/{id}")
    @ApiOperation(
            value = "Delete a Place by id",
            notes = "Provides an id to delete a specific Place",
            response = PlaceResource::class
    )
    override fun delete(
            @ApiParam(value = "ID value for the resource you need to delete")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result = placeService.delete(id)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 Place '${id}' not found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }

    @DeleteMapping("/places")
    @ApiOperation(
            value = "Delete all Places",
            notes = "Deletes all entries",
            response = PlaceResource::class
    )
    override fun deleteAll(): ResponseEntity<Any> {
        val result = placeService.deleteAll()
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 No Place found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }
}