package com.ataraxia.gabriel_vz.root

abstract class Service<M> {

    abstract fun getAll(): MutableList<M>

    abstract fun get(id: String): M

    abstract fun create(work: M): M

    abstract fun update(id: String, work: M): M

    abstract fun deleteAll()

    abstract fun delete(id: String)
}