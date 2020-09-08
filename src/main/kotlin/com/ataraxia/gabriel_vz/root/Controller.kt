package com.ataraxia.gabriel_vz.root

import com.ataraxia.gabriel_vz.model.Work
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

abstract class Controller<M, R> {

    abstract fun all(): ResponseEntity<List<R>>

    abstract fun one(id: String): ResponseEntity<R>

    abstract fun create(m: M): ResponseEntity<R>

    abstract fun update(id: String, worK: Work): ResponseEntity<R>

    abstract fun delete(id: String): ResponseEntity<HttpStatus>

    abstract fun deleteAll(): ResponseEntity<HttpStatus>

}