package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.Model
import java.time.OffsetDateTime

class Place(
        id: String? = null,
        title: String? = null,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var name: String? = null,
        var locality: String? = null,
        var country: String? = null,
        var coordinates: Coordinates? = Coordinates(),
        var relatedWorks: Map<String?, String?> = mutableMapOf()
) : Model(
        id,
        title,
        created,
        modified
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Place) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (locality != other.locality) return false
        if (country != other.country) return false
        if (coordinates != other.coordinates) return false

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
        return "Place(name=$name, locality=$locality, country=$country, coordinates=$coordinates, relatedWorks=$relatedWorks)"
    }


}