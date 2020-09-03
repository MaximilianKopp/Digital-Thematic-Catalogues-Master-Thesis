package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Place(
        id: String,
        val name: String,
        val locality: String,
        val country: String,
        val coordinates: Coordinates
) : AbstractEntity(id)