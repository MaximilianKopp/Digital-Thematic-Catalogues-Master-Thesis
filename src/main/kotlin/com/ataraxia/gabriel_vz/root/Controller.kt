package com.ataraxia.gabriel_vz.root

import org.springframework.http.ResponseEntity

abstract class Controller<M> {

    abstract fun all(): ResponseEntity<Any>

    abstract fun one(id: String): ResponseEntity<Any>

    abstract fun create(m: M): ResponseEntity<Any>

    abstract fun update(id: String, m: M): ResponseEntity<Any>

    abstract fun delete(id: String): ResponseEntity<Any>

    abstract fun deleteAll(): ResponseEntity<Any>

}