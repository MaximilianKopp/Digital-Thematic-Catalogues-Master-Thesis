package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import org.hibernate.mapping.Join
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

        @JsonBackReference
        @ManyToMany(cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE
        ])
        @JoinTable(
                joinColumns = [JoinColumn(name = "work_id")],
                inverseJoinColumns = [JoinColumn(name = "person_id")]
        )
        var relatedWorks: MutableList<WorkEntity>? = mutableListOf(),

        @ManyToMany(cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE
        ])
        @JoinTable(
                joinColumns = [JoinColumn(name = "discography_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "musician_id", referencedColumnName = "id")]
        )
        var relatedDiscographies: MutableList<DiscographyEntity>? = mutableListOf()
)