package com.ataraxia.gabriel_vz.controller.API

import arrow.core.Either
import com.ataraxia.gabriel_vz.errorhandling.ResourceNotFoundException
import com.ataraxia.gabriel_vz.factory.IncipitFactory
import com.ataraxia.gabriel_vz.model.Incipit
import com.ataraxia.gabriel_vz.resource.IncipitResource
import com.ataraxia.gabriel_vz.root.Controller
import com.ataraxia.gabriel_vz.service.IncipitService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(value = "/incipits", description = "Operations about Incipits")
@RequestMapping("/api")
class IncipitApiController(
        private val incipitFactory: IncipitFactory,
        private val incipitService: IncipitService
) : Controller<Incipit>() {

    @GetMapping("/incipits")
    @ApiOperation(
            value = "Find all Incipits",
            notes = "Provides a list of all Incipits",
            response = IncipitResource::class
    )
    override fun all(): ResponseEntity<Any> {
        val result: Either<Exception, List<IncipitResource>> = incipitService.getAll()
                .map { it.map(incipitFactory::resourceFromModel) }
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 No Incipit found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @GetMapping("/incipits/{id}")
    @ApiOperation(
            value = "Find a Incipit by id",
            notes = "Provides an id to look up a specific Incipit",
            response = IncipitResource::class
    )
    override fun one(
            @ApiParam(value = "ID value for the resource you need to retrieve")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result: Either<Exception, IncipitResource> = incipitService.get(id)
                .map(incipitFactory::resourceFromModel)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 Incipit '$id' not found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @PostMapping("/incipits", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(
            value = "Create an Incipit by id",
            notes = "Provides an id to create a specific Incipit",
            response = IncipitResource::class
    )
    override fun create(
            @ApiParam(value = "Model value for the Resource you need to create")
            @RequestBody m: Incipit): ResponseEntity<Any> {
        val result = incipitService.create(m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.CREATED) }
        )
    }

    @PutMapping("/incipits/{id}")
    @ApiOperation(
            value = "Update an Incipit by id",
            notes = "Provides an id to update a specific Incipit",
            response = IncipitResource::class
    )
    override fun update(
            @ApiParam(value = "ID value for the Resource you need to retrieve")
            @PathVariable("id") id: String,
            @ApiParam(value = "Model value for the Resource you need to update")
            @RequestBody m: Incipit): ResponseEntity<Any> {
        val result = incipitService.update(id, m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(m, HttpStatus.ACCEPTED) }
        )
    }

    @DeleteMapping("/incipits/{id}")
    @ApiOperation(
            value = "Delete an Incipit by id",
            notes = "Provides an id to delete a specific Incipit",
            response = IncipitResource::class
    )
    override fun delete(
            @ApiParam(value = "ID value for the resource you need to delete")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result = incipitService.delete(id)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 Incipit '${id}' not found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }

    @DeleteMapping("/incipits")
    @ApiOperation(
            value = "Delete all Incipits",
            notes = "Deletes all entries",
            response = IncipitResource::class
    )
    override fun deleteAll(): ResponseEntity<Any> {
        val result = incipitService.deleteAll()
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 No Incipits found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }
}