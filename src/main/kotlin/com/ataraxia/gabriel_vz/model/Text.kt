package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.Model
import java.time.OffsetDateTime

class Text(
        id: String? = null,
        title: String? = null,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var author: String? = null,
        var excerpt: String? = null,
        var relatedWorks: Map<String?, String?> = mutableMapOf()
) : Model(
        id,
        title,
        created,
        modified
) {

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