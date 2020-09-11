package com.ataraxia.gabriel_vz.controller.API

import arrow.core.Either
import com.ataraxia.gabriel_vz.errorhandling.ResourceNotFoundException
import com.ataraxia.gabriel_vz.factory.TextFactory
import com.ataraxia.gabriel_vz.model.Text
import com.ataraxia.gabriel_vz.resource.TextResource
import com.ataraxia.gabriel_vz.root.Controller
import com.ataraxia.gabriel_vz.service.TextService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(value = "/texts", description = "Operations about Texts")
@RequestMapping("/api")
class TextApiController(
        private val textFactory: TextFactory,
        private val textService: TextService
) : Controller<Text>() {

    @GetMapping("/texts")
    @ApiOperation(
            value = "Find all Texts",
            notes = "Provides a list of all Texts",
            response = TextResource::class
    )
    override fun all(): ResponseEntity<Any> {
        val result: Either<Exception, List<TextResource>> = textService.getAll()
                .map { it.map(textFactory::resourceFromModel) }
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 No Text found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @GetMapping("/texts/{id}")
    @ApiOperation(
            value = "Find a Text by id",
            notes = "Provides an id to look up a specific text",
            response = TextResource::class
    )
    override fun one(
            @ApiParam(value = "ID value for the resource you need to retrieve")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result: Either<Exception, TextResource> = textService.get(id)
                .map(textFactory::resourceFromModel)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 Text '$id' not found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @PostMapping("/texts", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(
            value = "Create a Text by id",
            notes = "Provides an id to create a specific text",
            response = TextResource::class
    )
    override fun create(
            @ApiParam(value = "Model value for the resource you need to create")
            @RequestBody m: Text): ResponseEntity<Any> {
        val result = textService.create(m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.CREATED) }
        )
    }

    @PutMapping("/texts/{id}")
    @ApiOperation(
            value = "Update a Text by id",
            notes = "Provides an id to update a specific Text",
            response = TextResource::class
    )
    override fun update(
            @ApiParam(value = "ID value for the resource you need to retrieve")
            @PathVariable("id") id: String,
            @ApiParam(value = "Model value for the resource you need to update")
            @RequestBody m: Text): ResponseEntity<Any> {
        val result = textService.update(id, m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(m, HttpStatus.ACCEPTED) }
        )
    }

    @DeleteMapping("/texts/{id}")
    @ApiOperation(
            value = "Delete a Text by id",
            notes = "Provides an id to delete a specific text",
            response = TextResource::class
    )
    override fun delete(
            @ApiParam(value = "ID value for the resource you need to delete")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result = textService.delete(id)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 Text '${id}' not found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }

    @DeleteMapping("/texts")
    @ApiOperation(
            value = "Delete all Texts",
            notes = "Deletes all entries",
            response = TextResource::class
    )
    override fun deleteAll(): ResponseEntity<Any> {
        val result = textService.deleteAll()
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 No Texts found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }
}