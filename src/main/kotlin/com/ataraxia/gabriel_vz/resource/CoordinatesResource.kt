package com.ataraxia.gabriel_vz.resource

class CoordinatesResource(
        var longitude: Double,
        var latitude: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CoordinatesResource) return false

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