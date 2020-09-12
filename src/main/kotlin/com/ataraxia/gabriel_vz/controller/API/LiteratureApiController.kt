package com.ataraxia.gabriel_vz.controller.API

import arrow.core.Either
import com.ataraxia.gabriel_vz.errorhandling.ResourceNotFoundException
import com.ataraxia.gabriel_vz.factory.LiteratureFactory
import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.resource.LiteratureResource
import com.ataraxia.gabriel_vz.resource.TextResource
import com.ataraxia.gabriel_vz.root.ApiController
import com.ataraxia.gabriel_vz.service.LiteratureService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(value = "/literature", description = "Operations about Literature")
@RequestMapping("/api/v1")
class LiteratureApiController(
        private val literatureFactory: LiteratureFactory,
        private val literatureService: LiteratureService
) : ApiController<Literature>() {

    @GetMapping("/literature")
    @ApiOperation(
            value = "Find all Literature",
            notes = "Provides a list of all Literature",
            response = LiteratureResource::class
    )
    override fun all()
            : ResponseEntity<Any> {
        val result: Either<Exception, List<LiteratureResource>> = literatureService.getAll()
                .map { it.map(literatureFactory::resourceFromModel) }
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 No Literature found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @GetMapping("/literature/{id}")
    @ApiOperation(
            value = "Find literature by id",
            notes = "Provides an id to look up specific literature",
            response = LiteratureResource::class
    )
    override fun one(
            @ApiParam(value = "ID value for the resource you need to retrieve", required = true)
            @PathVariable("id") id: String)
            : ResponseEntity<Any> {
        val result: Either<Exception, LiteratureResource> = literatureService.get(id)
                .map(literatureFactory::resourceFromModel)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 Literature '$id' not found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @PostMapping("/literature", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(
            value = "Create Literature by id",
            notes = "Provides an id to create specific literature",
            response = LiteratureResource::class
    )
    override fun create(
            @ApiParam(value = "Model value for the resource you need to create")
            @RequestBody m: Literature): ResponseEntity<Any> {
        val result = literatureService.create(m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.CREATED) }
        )
    }

    @PutMapping("/literature/{id}")
    @ApiOperation(
            value = "Update Literature by id",
            notes = "Provides an id to update specific Literature",
            response = TextResource::class
    )
    override fun update(
            @ApiParam(value = "ID value for the resource you need to retrieve")
            @PathVariable("id") id: String,
            @ApiParam(value = "Model value for the resource you need to update")
            @RequestBody m: Literature): ResponseEntity<Any> {
        val result = literatureService.update(id, m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(m, HttpStatus.ACCEPTED) }
        )
    }

    @DeleteMapping("/literature/{id}")
    @ApiOperation(
            value = "Delete Literature by id",
            notes = "Provides an id to delete specific Literature",
            response = LiteratureResource::class
    )
    override fun delete(
            @ApiParam(value = "ID value for the resource you need to delete")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result = literatureService.delete(id)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 Literature '${id}' not found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }

    @DeleteMapping("/literature")
    @ApiOperation(
            value = "Delete all Literature",
            notes = "Deletes all entries",
            response = LiteratureResource::class
    )
    override fun deleteAll(): ResponseEntity<Any> {
        val result = literatureService.deleteAll()
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 No Literature found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }
}