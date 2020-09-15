package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "text")
class TextEntity(
        id: String?,
        title: String?,
        created: OffsetDateTime?,
        modified: OffsetDateTime?,
        var author: String?,
        var excerpt: String?,

        @JsonBackReference(value = "text-work")
        @OneToMany(
                mappedBy = "relatedText",
                orphanRemoval = true
        )
        var relatedWorks: MutableSet<WorkEntity>? = mutableSetOf()
) : com.ataraxia.gabriel_vz.root.Entity(
        id,
        title,
        created,
        modified
) {
    fun addWork(workEntity: WorkEntity) {
        relatedWorks?.add(workEntity)
        workEntity.relatedText = this
    }

    fun removeWork(workEntity: WorkEntity) {
        relatedWorks?.remove(workEntity)
        workEntity.relatedText = null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TextEntity) return false
        if (!super.equals(other)) return false

        if (author != other.author) return false
        if (excerpt != other.excerpt) return false
        if (relatedWorks != other.relatedWorks) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + excerpt.hashCode()
        return result
    }
    
}