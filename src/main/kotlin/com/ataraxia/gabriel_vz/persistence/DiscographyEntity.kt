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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DiscographyEntity) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (label != other.label) return false
        if (recordId != other.recordId) return false
        if (dateOfPublishing != other.dateOfPublishing) return false
        if (musicians != other.musicians) return false
        if (relatedWorks != other.relatedWorks) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + recordId.hashCode()
        result = 31 * result + dateOfPublishing.hashCode()
        result = 31 * result + (musicians?.hashCode() ?: 0)
        result = 31 * result + (relatedWorks?.hashCode() ?: 0)
        return result
    }


}
