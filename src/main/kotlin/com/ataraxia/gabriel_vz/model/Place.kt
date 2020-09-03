package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Place(
        id: String,
        var name: String,
        var locality: String,
        var country: String,
        var coordinates: Coordinates
) : AbstractEntity(id)