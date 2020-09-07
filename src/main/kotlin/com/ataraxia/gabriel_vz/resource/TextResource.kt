package com.ataraxia.gabriel_vz.resource

import org.springframework.hateoas.Link

class TextResource(
        var self: Link?,
        var id: String?,
        var title: String,
        var author: String,
        var excerpt: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TextResource) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (author != other.author) return false
        if (excerpt != other.excerpt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = self?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + title.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + excerpt.hashCode()
        return result
    }
}