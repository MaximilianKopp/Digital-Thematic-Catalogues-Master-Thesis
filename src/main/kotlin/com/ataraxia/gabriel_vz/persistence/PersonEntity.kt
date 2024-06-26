package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "person")
class PersonEntity(
        id: String? = null,
        title: String? = null,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var name: String? = null,
        var pnd: String? = null,
        var role: String? = null,
        var description: String? = null,

        @JsonBackReference(value = "work-person")
        @ManyToMany(mappedBy = "relatedPersons")
        var relatedWorks: MutableSet<WorkEntity>? = mutableSetOf(),

        @JsonBackReference
        @ManyToMany(cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE
        ])
        @JoinTable(
                joinColumns = [JoinColumn(name = "musician_id")],
                inverseJoinColumns = [JoinColumn(name = "discography_id")]
        )
        var relatedDiscographies: MutableSet<DiscographyEntity>? = mutableSetOf()
) : com.ataraxia.gabriel_vz.root.Entity(
        id,
        title,
        created,
        modified
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersonEntity) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (pnd != other.pnd) return false
        if (role != other.role) return false
        if (description != other.description) return false
        if (relatedWorks != other.relatedWorks) return false
        if (relatedDiscographies != other.relatedDiscographies) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + pnd.hashCode()
        result = 31 * result + role.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + (relatedDiscographies?.hashCode() ?: 0)
        return result
    }
}