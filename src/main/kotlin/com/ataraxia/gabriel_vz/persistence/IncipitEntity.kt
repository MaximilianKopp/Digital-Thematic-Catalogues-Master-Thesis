package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Incipit(
        id: String,
        text: String,
        keysig: String,
        timesig: String,
        score: String,
        description: String
) : AbstractEntity(id)