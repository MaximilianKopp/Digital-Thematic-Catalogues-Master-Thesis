package com.ataraxia.gabriel_vz.persistence

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
                joinColumns = [JoinColumn(name = "discography_id")],
                inverseJoinColumns = [JoinColumn(name = "musician_id")]
        )
        var relatedDiscographies: MutableList<DiscographyEntity>? = mutableListOf()
) {

    fun addWork(workEntity: WorkEntity) {
        relatedWorks?.add(workEntity)
        workEntity.relatedPersons?.add(this)
    }

    fun removeWork(workEntity: WorkEntity) {
        relatedWorks?.remove(workEntity)
        workEntity.relatedPersons?.remove(this)
    }

    fun addDiscography(discographyEntity: DiscographyEntity) {
        relatedDiscographies?.add(discographyEntity)
        discographyEntity.musicians?.add(this)
    }

    fun removeDiscography(discographyEntity: DiscographyEntity) {
        relatedDiscographies?.remove(discographyEntity)
        discographyEntity.musicians?.remove(this)
    }
}