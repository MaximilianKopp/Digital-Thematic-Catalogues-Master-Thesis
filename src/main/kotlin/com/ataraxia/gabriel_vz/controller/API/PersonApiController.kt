package com.ataraxia.gabriel_vz.controller.API

import arrow.core.Either
import com.ataraxia.gabriel_vz.errorhandling.ResourceNotFoundException
import com.ataraxia.gabriel_vz.factory.PersonFactory
import com.ataraxia.gabriel_vz.model.Person
import com.ataraxia.gabriel_vz.resource.PersonResource
import com.ataraxia.gabriel_vz.resource.TextResource
import com.ataraxia.gabriel_vz.root.Controller
import com.ataraxia.gabriel_vz.service.PersonService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@Api(value = "/persons", description = "Operations about Persons")
@RequestMapping("/api")
class PersonApiController(
        private val personFactory: PersonFactory,
        private val personService: PersonService
) : Controller<Person>() {

    @GetMapping("/persons")
    @ApiOperation(
            value = "Find all Persons",
            notes = "Provides a list of all Persons",
            response = PersonResource::class
    )
    override fun all(): ResponseEntity<Any> {
        val result: Either<Exception, List<PersonResource>> = personService.getAll()
                .map { it.map(personFactory::resourceFromModel) }
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 No Person found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @GetMapping("/persons/{id}")
    @ApiOperation(
            value = "Find a Person by id",
            notes = "Provides an id to look up a specific Person",
            response = PersonResource::class
    )
    override fun one(
            @ApiParam(value = "ID value for the resource you need to retrieve")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result: Either<Exception, PersonResource> = personService.get(id)
                .map(personFactory::resourceFromModel)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 Person '$id' not found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @PostMapping("/persons", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(
            value = "Create a Person by id",
            notes = "Provides an id to create a specific Person",
            response = TextResource::class
    )
    override fun create(
            @ApiParam(value = "Model value for the resource you need to create")
            @RequestBody m: Person): ResponseEntity<Any> {
        val result = personService.create(m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.CREATED) }
        )
    }

    @PutMapping("/persons/{id}")
    @ApiOperation(
            value = "Update a Person by id",
            notes = "Provides an id to update a specific Person",
            response = PersonResource::class
    )
    override fun update(
            @ApiParam(value = "ID value for the resource you need to retrieve")
            @PathVariable("id") id: String,
            @ApiParam(value = "Model value for the resource you need to update")
            @RequestBody m: Person): ResponseEntity<Any> {
        val result = personService.update(id, m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(m, HttpStatus.ACCEPTED) }
        )
    }

    @DeleteMapping("/persons/{id}")
    @ApiOperation(
            value = "Delete a Person by id",
            notes = "Provides an id to delete a specific Person",
            response = PersonResource::class
    )
    override fun delete(
            @ApiParam(value = "ID value for the resource you need to delete")
            @PathVariable("id") id: String): ResponseEntity<Any> {
        val result = personService.delete(id)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 Person '${id}' not found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }

    @DeleteMapping("/persons")
    @ApiOperation(
            value = "Delete all Persons",
            notes = "Deletes all entries",
            response = PersonResource::class
    )
    override fun deleteAll(): ResponseEntity<Any> {
        val result = personService.deleteAll()
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 No Persons found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }
}