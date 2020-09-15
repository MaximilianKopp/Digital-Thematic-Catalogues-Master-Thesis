package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.Model
import java.time.OffsetDateTime

class Literature(
        id: String?,
        title: String?,
        created: OffsetDateTime?,
        modified: OffsetDateTime?,
        var author: String?,
        var isbn: String?,
        var yearOfPublishing: String?
) : Model(
        id,
        title,
        created,
        modified
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Literature) return false
        if (!super.equals(other)) return false

        if (author != other.author) return false
        if (isbn != other.isbn) return false
        if (yearOfPublishing != other.yearOfPublishing) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + isbn.hashCode()
        result = 31 * result + yearOfPublishing.hashCode()
        return result
    }
}