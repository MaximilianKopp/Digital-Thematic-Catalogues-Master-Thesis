package com.ataraxia.gabriel_vz.root

abstract class Factory<M, E, R> {

    abstract fun modelFromEntity(entity: E): M

    abstract fun entityFromModel(model: M): E

    abstract fun modelFromResource(resource: R): M

    abstract fun resourceFromModel(model: M): R
}