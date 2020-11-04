package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "place")
class PlaceEntity(
        id: String? = null,
        title: String? = null,
        created: OffsetDateTime  = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var name: String? = null,
        var locality: String? = null,
        var country: String? = null,

        @Embedded
        @AttributeOverrides(
                AttributeOverride(name = "latitude", column = Column(name = "latitude")),
                AttributeOverride(name = "longitude", column = Column(name = "longitude"))
        )
        var coordinates: CoordinatesEntity? = CoordinatesEntity(),

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

    override fun toString(): String {
        return "PlaceEntity(name=$name, locality=$locality, country=$country, coordinates=$coordinates, relatedWorks=$relatedWorks)"
    }


}