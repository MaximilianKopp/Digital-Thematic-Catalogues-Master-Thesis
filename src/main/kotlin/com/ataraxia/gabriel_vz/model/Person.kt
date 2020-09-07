package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Person(
        override var id: String?,
        var name: String,
        var pnd: String,
        var role: String,
        var description: String,
        var relatedWorks: MutableSet<Work>,
        var relatedDiscographies: MutableSet<Discography>
) : AbstractEntity(id) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Person) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (pnd != other.pnd) return false
        if (role != other.role) return false
        if (description != other.description) return false
        if (relatedWorks != other.relatedWorks) return false
        if (relatedDiscographies != other.relatedDiscographies) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + pnd.hashCode()
        result = 31 * result + role.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + relatedWorks.hashCode()
        result = 31 * result + relatedDiscographies.hashCode()
        return result
    }
}