package com.ataraxia.gabriel_vz.controller

import arrow.core.extensions.option.foldable.isNotEmpty
import arrow.core.toOption
import com.ataraxia.gabriel_vz.factory.WorkFactory
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.resource.WorkResource
import com.ataraxia.gabriel_vz.root.Controller
import com.ataraxia.gabriel_vz.service.WorkService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class WorkController(
        private val workFactory: WorkFactory,
        private val workService: WorkService) : Controller<Work, WorkResource>() {

    @GetMapping("/works")
    override fun all(): ResponseEntity<List<WorkResource>> {
        return try {
            val works = workService
                    .getAll()
                    .map(workFactory::resourceFromModel)

            ResponseEntity(works, HttpStatus.OK)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/works/{id}")
    override fun one(@PathVariable("id") id: String)
            : ResponseEntity<WorkResource> {
        val work = workFactory.resourceFromModel(workService.get(id)).toOption()
        return if (work.isNotEmpty()) {
            ResponseEntity(work.orNull(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/works", produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun create(@RequestBody work: Work)
            : ResponseEntity<WorkResource> {
        return try {
            workService.create(work)
            ResponseEntity(workFactory.resourceFromModel(work), HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/works/{id}")
    override fun update(@PathVariable("id") id: String, @RequestBody work: Work)
            : ResponseEntity<WorkResource> {
        return try {
            val workdata = workService.update(id, work)
            ResponseEntity(workFactory.resourceFromModel(workdata), HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/works")
    override fun deleteAll(): ResponseEntity<HttpStatus> {
        return try {
            workService.deleteAll()
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/works/{id}")
    override fun delete(@PathVariable("id") id: String): ResponseEntity<HttpStatus> {
        return try {
            workService.delete(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}