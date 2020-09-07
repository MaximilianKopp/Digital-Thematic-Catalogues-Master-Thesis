package com.ataraxia.gabriel_vz.resource

import com.ataraxia.gabriel_vz.model.Coordinates
import org.springframework.hateoas.Link

class PlaceResource(
        var self: Link?,
        var id: String?,
        var name: String,
        var locality: String,
        var country: String,
        var coordinates: CoordinatesResource
)