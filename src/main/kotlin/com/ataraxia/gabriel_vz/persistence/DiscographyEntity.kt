package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "discography")
class DiscographyEntity(

        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String? = null,
        var title: String,
        var label: String,
        var recordId: String,
        var dateOfPublishing: String,

        @JsonManagedReference
        @ManyToMany(mappedBy = "relatedDiscographies",
                cascade = [CascadeType.ALL])
        var musicians: MutableSet<PersonEntity>? = mutableSetOf(),

        @JsonBackReference(value = "work-discography")
        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(
                joinColumns = [JoinColumn(name = "work_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "discography_id", referencedColumnName = "id")])
        var relatedWorks: MutableSet<WorkEntity>? = mutableSetOf()
) {
    fun addMusicians(personEntity: PersonEntity) {
        musicians?.add(personEntity)
        personEntity.relatedDiscographies?.add(this)
    }

    fun removeMusicians(personEntity: PersonEntity) {
        musicians?.remove(personEntity)
        personEntity.relatedDiscographies?.remove(this)
    }
}
