package com.ataraxia.gabriel_vz.root

abstract class AbstractEntity(
        override val id: String?
) : EntityInterface {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}