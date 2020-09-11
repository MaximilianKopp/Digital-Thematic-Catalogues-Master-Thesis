package com.ataraxia.gabriel_vz.persistence

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Embeddable
class CoordinatesEntity(
        @Column(name = "longitude")
        var longitude: Double,
        @Column(name = "latitude")
        var latitude: Double
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CoordinatesEntity) return false

        if (longitude != other.longitude) return false
        if (latitude != other.latitude) return false

        return true
    }

    override fun hashCode(): Int {
        return 31
    }
}