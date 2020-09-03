package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Person(
        id: String,
        val name: String,
        val pnd: String,
        val function: String,
        val description: String,
        val relatedWorks: ArrayList<Work>,
        val relatedDiscographies: ArrayList<Discography>
) : AbstractEntity(id)