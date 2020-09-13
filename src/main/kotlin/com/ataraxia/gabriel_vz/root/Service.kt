package com.ataraxia.gabriel_vz.root

import arrow.core.Either

abstract class Service<M> {

    abstract fun getAll(): Either<Exception, MutableList<M>>

    abstract fun get(id: String): Either<Exception, M>

    abstract fun create(m: M): Either<Exception, M>

    abstract fun update(id: String, m: M): Either<Exception, M>

    abstract fun deleteAll(): Either<Exception, Unit>

    abstract fun delete(id: String): Either<Exception, Unit>
}