package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "place")
class PlaceEntity(
        id: String?,
        title: String?,
        created: OffsetDateTime?,
        modified: OffsetDateTime?,
        var name: String,
        var locality: String,
        var country: String,

        @Embedded
        @AttributeOverrides(
                AttributeOverride(name = "latitude", column = Column(name = "latitude")),
                AttributeOverride(name = "longitude", column = Column(name = "longitude"))
        )
        var coordinates: CoordinatesEntity,

        @JsonBackReference
        @OneToMany(
                mappedBy = "placeOfPremiere"
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
        workEntity.placeOfPremiere = this
    }

    fun removeWork(workEntity: WorkEntity) {
        relatedWorks?.remove(workEntity)
        workEntity.placeOfPremiere = null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PlaceEntity) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (locality != other.locality) return false
        if (country != other.country) return false
        if (coordinates != other.coordinates) return false
        if (relatedWorks != other.relatedWorks) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + locality.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + coordinates.hashCode()
        return result
    }
    
}