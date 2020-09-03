package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Work(
        id: String,
        val title: String,
        val dateOfCreation: String,
        val dateOfPremiere: String,
        val placeOfPremiere: Place,
        val commentary: String,
        val dedication: String,
        val instrumentation: String,
        val category: String,
        val duration: String,
        val editor: String,
        val text: Text,
        val discographies: ArrayList<Discography>,
        val relatedPersons: ArrayList<Person>,
        val literature: ArrayList<Literature>
) : AbstractEntity(id)