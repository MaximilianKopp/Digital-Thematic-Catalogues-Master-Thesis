package com.ataraxia.gabriel_vz.persistence

import arrow.core.Option
import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import java.time.OffsetDateTime
import javax.persistence.*

@Table
@Entity(name = "literature")
class LiteratureEntity(
        id: String?,
        title: String?,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var author: String?,
        var isbn: String?,
        var yearOfPublishing: String?,

        @JsonBackReference
        @ManyToOne(fetch = FetchType.LAZY)
        var relatedWork: WorkEntity? = Option.empty<WorkEntity?>().orNull()
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
        if (relatedWork != other.relatedWork) return false

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