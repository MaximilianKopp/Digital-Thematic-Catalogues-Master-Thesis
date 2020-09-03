package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Person(
        id: String,
        var name: String,
        var pnd: String,
        var role: String,
        var description: String,
        var relatedWorks: ArrayList<Work>,
        var relatedDiscographies: ArrayList<Discography>
) : AbstractEntity(id)