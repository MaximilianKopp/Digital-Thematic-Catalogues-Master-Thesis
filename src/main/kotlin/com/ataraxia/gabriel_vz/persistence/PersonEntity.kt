package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "person")
class PersonEntity(

        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String? = null,
        var name: String,
        var pnd: String,
        var role: String,
        var description: String,

        @JsonBackReference(value = "work-person")
        @ManyToMany(cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE
        ])
        @JoinTable(
                joinColumns = [JoinColumn(name = "person_id")],
                inverseJoinColumns = [JoinColumn(name = "work_id")]
        )
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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersonEntity) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (pnd != other.pnd) return false
        if (role != other.role) return false
        if (description != other.description) return false
        if (relatedDiscographies != other.relatedDiscographies) return false

        return true
    }

    override fun hashCode(): Int {
        return 31
    }
}