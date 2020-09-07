package com.ataraxia.gabriel_vz.resource

import com.ataraxia.gabriel_vz.model.Person
import org.springframework.hateoas.Link

class DiscographyResource(
        var self: Link? = null,
        var id: String?,
        var title: String,
        var label: String,
        var recordId: String,
        var dateOfPublishing: String,
        var musicians: MutableSet<PersonResource>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DiscographyResource) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (label != other.label) return false
        if (recordId != other.recordId) return false
        if (dateOfPublishing != other.dateOfPublishing) return false
        if (musicians != other.musicians) return false

        return true
    }

    override fun hashCode(): Int {
        var result = self?.hashCode() ?: 0
        result = 31 * result + id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + recordId.hashCode()
        result = 31 * result + dateOfPublishing.hashCode()
        result = 31 * result + musicians.hashCode()
        return result
    }
}