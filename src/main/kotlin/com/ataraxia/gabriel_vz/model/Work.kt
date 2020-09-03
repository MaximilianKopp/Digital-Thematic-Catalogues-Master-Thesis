package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Work(
        id: String,
        var title: String,
        var dateOfCreation: String,
        var dateOfPremiere: String,
        var placeOfPremiere: Place?,
        var commentary: String,
        var dedication: String,
        var instrumentation: String,
        var category: String,
        var duration: String,
        var editor: String,
        var text: Text?,
        var discographies: ArrayList<Discography>,
        var relatedPersons: ArrayList<Person>,
        var literature: ArrayList<Literature>
) : AbstractEntity(id)