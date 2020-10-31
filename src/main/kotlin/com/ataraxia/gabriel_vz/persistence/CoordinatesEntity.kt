package com.ataraxia.gabriel_vz.persistence

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CoordinatesEntity(
        @Column(name = "longitude")
        var longitude: Double? = null,
        @Column(name = "latitude")
        var latitude: Double? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CoordinatesEntity) return false

        if (longitude != other.longitude) return false
        if (latitude != other.latitude) return false

        return true
    }

    override fun hashCode(): Int {
        var result = longitude.hashCode()
        result = 31 * result + latitude.hashCode()
        return result
    }
}