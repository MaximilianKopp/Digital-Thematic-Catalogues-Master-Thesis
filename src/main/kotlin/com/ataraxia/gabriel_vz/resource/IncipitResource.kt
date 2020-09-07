package com.ataraxia.gabriel_vz.resource

import org.springframework.hateoas.Link

class IncipitResource(

        var self: Link?,
        var id: String?,
        var text: String,
        var keysig: String,
        var timesig: String,
        var score: String,
        var description: String
)