package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Text(
        id: String,
        var title: String,
        var author: String,
        var excerpt: String
) : AbstractEntity(id)