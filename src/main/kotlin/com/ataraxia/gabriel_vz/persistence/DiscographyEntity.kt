package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "discography")
class DiscographyEntity(
        id: String? = null ,
        title: String? = null,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var label: String? = null,
        var recordId: String? = null,
        var dateOfPublishing: String? = null,

        @JsonManagedReference
        @ManyToMany(mappedBy = "relatedDiscographies",
                fetch = FetchType.LAZY,
                cascade = [
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE])
        var musicians: MutableSet<PersonEntity>? = mutableSetOf(),

        @JsonBackReference(value = "work-discography")
        @ManyToMany(mappedBy = "discographies")
        var relatedWorks: MutableSet<WorkEntity>? = mutableSetOf()

) : com.ataraxia.gabriel_vz.root.Entity(
        id,
        title,
        created,
        modified
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
        if (!super.equals(other)) return false

        if (label != other.label) return false
        if (recordId != other.recordId) return false
        if (dateOfPublishing != other.dateOfPublishing) return false
        if (musicians != other.musicians) return false
        if (relatedWorks != other.relatedWorks) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + (recordId?.hashCode() ?: 0)
        result = 31 * result + (dateOfPublishing?.hashCode() ?: 0)
        result = 31 * result + (musicians?.hashCode() ?: 0)
        result = 31 * result + (relatedWorks?.hashCode() ?: 0)
        return result
    }


}
