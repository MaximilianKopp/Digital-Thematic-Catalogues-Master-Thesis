package com.ataraxia.gabriel_vz.controller.API

import arrow.core.Either
import com.ataraxia.gabriel_vz.errorhandling.ResourceNotFoundException
import com.ataraxia.gabriel_vz.factory.WorkFactory
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.persistence.WorkEntity
import com.ataraxia.gabriel_vz.repository.WorkRepository
import com.ataraxia.gabriel_vz.resource.WorkResource
import com.ataraxia.gabriel_vz.root.ApiController
import com.ataraxia.gabriel_vz.service.WorkService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.JsonParseException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.EntityLinks
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder

@RestController
@Api(value = "/works", description = "Operations about Works")
@RequestMapping("/api/v1")
class WorkApiController(
        private val workFactory: WorkFactory,
        private val workService: WorkService
) : ApiController<Work>() {

    @Autowired
    lateinit var workRepository: WorkRepository

    @GetMapping("/works")
    @ApiOperation(
            value = "Find all Works",
            notes = "Provides a list of all Works",
            response = WorkResource::class
    )
    override fun all()
            : ResponseEntity<Any> {
        val result: Either<Exception, List<WorkResource>> = workService.getAll()
                .map { it.map(workFactory::resourceFromModel) }
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 No Works found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @GetMapping("/works/{id}")
    @ApiOperation(
            value = "Find a Work by id",
            notes = "Provides an id to look up a specific work",
            response = WorkResource::class
    )
    override fun one(
            @ApiParam(value = "ID value for the Work you need to retrieve", required = true)
            @PathVariable("id") id: String)
            : ResponseEntity<Any> {
        val result: Either<Exception, WorkResource> = workService.get(id)
                .map(workFactory::resourceFromModel)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("Error 404 Work '$id' not found"), HttpStatus.NOT_FOUND) },
                ifRight = { ResponseEntity(it, HttpStatus.OK) }
        )
    }

    @PostMapping("/works", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(
            value = "Create a Work by id",
            notes = "Provides an id to create a specific work",
            response = WorkResource::class
    )
    override fun create(
            @ApiParam(value = "Model value for the Resource you need to create")
            @RequestBody m: Work)
            : ResponseEntity<Any> {
        val result = workService.create(m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.CREATED) }
        )
    }

    @PutMapping("/works/{id}")
    @ApiOperation(
            value = "Update a Work by id",
            notes = "Provides an id to update a specific work",
            response = WorkResource::class
    )
    override fun update(
            @ApiParam(value = "ID value for the Resource you need to retrieve")
            @PathVariable("id") id: String,
            @ApiParam(value = "Model value for the Resource you need to update")
            @RequestBody m: Work)
            : ResponseEntity<Any> {
        val result = workService.update(id, m)
        return result.fold(
                ifLeft = { ResponseEntity(JsonParseException(), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(m, HttpStatus.ACCEPTED) }
        )
    }

    @DeleteMapping("/works/{id}")
    @ApiOperation(
            value = "Delete a Work by id",
            notes = "Provides an id to delete a specific work",
            response = WorkResource::class
    )
    override fun delete(
            @ApiParam(value = "ID value for the resource you need to delete")
            @PathVariable("id") id: String)
            : ResponseEntity<Any> {
        val result = workService.delete(id)
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 Work '${id}' not found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }

    @DeleteMapping("/works")
    @ApiOperation(
            value = "Delete all Works",
            notes = "Deletes all entries",
            response = WorkResource::class
    )
    override fun deleteAll()
            : ResponseEntity<Any> {
        val result = workService.deleteAll()
        return result.fold(
                ifLeft = { ResponseEntity(ResourceNotFoundException("404 No Works found"), HttpStatus.BAD_REQUEST) },
                ifRight = { ResponseEntity(Unit, HttpStatus.OK) }
        )
    }
}