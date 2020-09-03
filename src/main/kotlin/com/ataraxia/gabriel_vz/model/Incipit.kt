package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Incipit(
        id: String,
        var text: String,
        var keysig: String,
        var timesig: String,
        var score: String,
        var description: String
) : AbstractEntity(id)