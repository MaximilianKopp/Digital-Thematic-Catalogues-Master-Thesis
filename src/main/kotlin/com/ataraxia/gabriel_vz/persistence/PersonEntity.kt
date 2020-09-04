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
                joinColumns = [JoinColumn(name = "work_id")],
                inverseJoinColumns = [JoinColumn(name = "person_id")]
        )
        var relatedWorks: MutableSet<WorkEntity>? = mutableSetOf(),

        @JsonBackReference
        @ManyToMany(cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE
        ])
        @JoinTable(
                joinColumns = [JoinColumn(name = "discography_id")],
                inverseJoinColumns = [JoinColumn(name = "musician_id")]
        )
        var relatedDiscographies: MutableSet<DiscographyEntity>? = mutableSetOf()
)