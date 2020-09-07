package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Place(
        override var id: String?,
        var name: String,
        var locality: String,
        var country: String,
        var coordinates: Coordinates
) : AbstractEntity(id) {

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
}