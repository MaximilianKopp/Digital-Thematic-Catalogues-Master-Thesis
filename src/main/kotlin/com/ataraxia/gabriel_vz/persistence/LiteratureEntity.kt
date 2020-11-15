package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.OffsetDateTime
import javax.persistence.*

@Table
@Entity(name = "literature")
class LiteratureEntity(
        id: String? = null,
        title: String? = null,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var author: String? = null,
        var isbn: String? = null,
        var yearOfPublishing: String? = null,

        @JsonBackReference
        @ManyToMany(mappedBy = "literatureList")
        var relatedWorks: MutableSet<WorkEntity>? = mutableSetOf()
) : com.ataraxia.gabriel_vz.root.Entity(
        id,
        title,
        created,
        modified
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LiteratureEntity) return false
        if (!super.equals(other)) return false

        if (author != other.author) return false
        if (isbn != other.isbn) return false
        if (yearOfPublishing != other.yearOfPublishing) return false
        if (relatedWorks != other.relatedWorks) return false

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