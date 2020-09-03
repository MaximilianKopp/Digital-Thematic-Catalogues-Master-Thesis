package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Literature(
        id: String,
        val author: String,
        val isbn: String,
        val yearOfPublishing: String
) : AbstractEntity(id)