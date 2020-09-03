package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Literature(
        id: String,
        var author: String,
        var isbn: String,
        var yearOfPublishing: String
) : AbstractEntity(id)