package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Discography(
        id: String,
        var title: String,
        var label: String,
        var recordId: String,
        var dateOfPublishing: String,
        var musicians: ArrayList<Person>
) : AbstractEntity(id)