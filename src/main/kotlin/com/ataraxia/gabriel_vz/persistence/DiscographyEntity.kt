package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Discography(
        id: String,
        val title: String,
        val label: String,
        val recordId: String,
        val dateOfPublishing: String,
        val musicians: ArrayList<Person>
) : AbstractEntity(id)