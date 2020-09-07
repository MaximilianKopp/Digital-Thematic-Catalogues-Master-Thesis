package com.ataraxia.gabriel_vz.resource

import com.ataraxia.gabriel_vz.model.Discography
import com.ataraxia.gabriel_vz.model.Work
import org.springframework.hateoas.Link

class PersonResource(
        var self: Link?,
        var id: String?,
        var name: String,
        var pnd: String,
        var role: String,
        var description: String,
        var relatedWorks: MutableSet<WorkResource>,
        var relatedDiscographies: MutableSet<DiscographyResource>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersonResource) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (pnd != other.pnd) return false
        if (role != other.role) return false
        if (description != other.description) return false
        if (relatedWorks != other.relatedWorks) return false
        if (relatedDiscographies != other.relatedDiscographies) return false

        return true
    }

    override fun hashCode(): Int {
        var result = self?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + pnd.hashCode()
        result = 31 * result + role.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + relatedWorks.hashCode()
        result = 31 * result + relatedDiscographies.hashCode()
        return result
    }
}