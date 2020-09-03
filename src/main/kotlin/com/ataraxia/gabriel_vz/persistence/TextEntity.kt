package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Text(
        id: String,
        val title: String,
        val author: String,
        val excerpt: String
) : AbstractEntity(id)