package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.Model
import java.time.OffsetDateTime

class Person(
        id: String? = null,
        title: String? = null,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var name: String? = null,
        var pnd: String? = null,
        var role: String? = null,
        var description: String? = null,
        var relatedWorks: Map<String?, String?> = mutableMapOf(),
        var relatedDiscographies: Map<String?, String?> = mutableMapOf()
) : Model(
        id,
        title,
        created,
        modified
) {

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