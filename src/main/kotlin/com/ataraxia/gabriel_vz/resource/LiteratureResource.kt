package com.ataraxia.gabriel_vz.resource

import org.springframework.hateoas.Link

class LiteratureResource(
        var self: Link?,
        var id: String?,
        var author: String,
        var isbn: String,
        var yearOfPublishing: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LiteratureResource) return false

        if (id != other.id) return false
        if (author != other.author) return false
        if (isbn != other.isbn) return false
        if (yearOfPublishing != other.yearOfPublishing) return false

        return true
    }

    override fun hashCode(): Int {
        var result = self?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + author.hashCode()
        result = 31 * result + isbn.hashCode()
        result = 31 * result + yearOfPublishing.hashCode()
        return result
    }
}