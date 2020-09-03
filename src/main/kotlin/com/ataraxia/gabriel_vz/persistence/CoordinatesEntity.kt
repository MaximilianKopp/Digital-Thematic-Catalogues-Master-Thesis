package com.ataraxia.gabriel_vz.persistence

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Embeddable
class CoordinatesEntity(
        @Column(name = "longitude")
        var longitude: Double,
        @Column(name = "latitude")
        var latitude: Double
)