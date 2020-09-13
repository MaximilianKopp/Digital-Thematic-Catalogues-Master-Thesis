package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Text(
        override var id: String?,
        var title: String,
        var author: String,
        var excerpt: String
) : AbstractEntity(id) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Text) return false
        if (!super.equals(other)) return false

        if (title != other.title) return false
        if (author != other.author) return false
        if (excerpt != other.excerpt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + excerpt.hashCode()
        return result
    }
}