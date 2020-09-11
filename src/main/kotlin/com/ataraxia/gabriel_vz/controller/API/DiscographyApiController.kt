package com.ataraxia.gabriel_vz.controller.API

import arrow.core.Either
import com.ataraxia.gabriel_vz.errorhandling.ResourceNotFoundException
import com.ataraxia.gabriel_vz.factory.DiscographyFactory
import com.ataraxia.gabriel_vz.model.Discography
import com.ataraxia.gabriel_vz.resource.DiscographyResource
import com.ataraxia.gabriel_vz.root.Controller
import com.ataraxia.gabriel_vz.service.DiscographyService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(value = "/discographies")
@RequestMapping("/api")
class DiscographyApiController(
        private val discographyFactory: DiscographyFactory,
        private val discographyService: DiscographyService
) : Controller<Discography>() {

    @GetMapping("/discographies")
    @ApiOperation(
            value = "Find all Discographies",
            notes = "Provides a list of all Discographies",
            response = DiscographyResource::class
    )
    override fun all(): ResponseEntity<Any> {
        val result: Either<Exception, List<DiscographyResource>> = discographyService.getAll()
                .map { it.map(discographyFactory::resourceFromModel) }
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 No Discography found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @GetMapping("/discographies/{id}")
    @ApiOperation(
            value = "Find a Discography by id",
            notes = "Provides an id to look up a specific Discography",
            response = DiscographyResource::class
    )
    override fun one(
            @ApiParam(value = "ID value for the resource you need to retrieve")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result: Either<Exception, DiscographyResource> = discographyService.get(id)
                .map(discographyFactory::resourceFromModel)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 Discography '$id' not found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @PostMapping("/discographies", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(
            value = "Create a Discography by id",
            notes = "Provides an id to create a specific Discography",
            response = DiscographyResource::class
    )
    override fun create(
            @ApiParam(value = "Model value for the resource you need to create")
            @RequestBody m: Discography): ResponseEntity<Any> {
        val result = discographyService.create(m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.CREATED) }
        )
    }

    @PutMapping("/discographies/{id}")
    @ApiOperation(
            value = "Update a Discography by id",
            notes = "Provides an id to update a specific Discography",
            response = DiscographyResource::class
    )
    override fun update(
            @ApiParam(value = "ID value for the resource you need to retrieve")
            @PathVariable("id") id: String,
            @ApiParam(value = "Model value for the resource you need to update")
            @RequestBody m: Discography): ResponseEntity<Any> {
        val result = discographyService.update(id, m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(m, HttpStatus.ACCEPTED) }
        )
    }

    @DeleteMapping("/discographies/{id}")
    @ApiOperation(
            value = "Delete a Discography by id",
            notes = "Provides an id to delete a specific Discography",
            response = DiscographyResource::class
    )
    override fun delete(
            @ApiParam(value = "ID value for the resource you need to delete")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result = discographyService.delete(id)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 Discography '${id}' not found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }

    @DeleteMapping("/discographies")
    @ApiOperation(
            value = "Delete all Discographies",
            notes = "Deletes all Discographies",
            response = DiscographyResource::class
    )
    override fun deleteAll(): ResponseEntity<Any> {
        val result = discographyService.deleteAll()
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 No Texts found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }
}