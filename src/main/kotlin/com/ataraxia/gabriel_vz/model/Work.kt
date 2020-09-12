package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Work(
        override var id: String?,
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
        var relatedText: Text?,
        var discographies: MutableSet<Discography>,
        var relatedPersons: MutableSet<Person>,
        var literatureList: MutableSet<Literature>
) : AbstractEntity(id) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Work) return false
        if (!super.equals(other)) return false

        if (title != other.title) return false
        if (dateOfCreation != other.dateOfCreation) return false
        if (dateOfPremiere != other.dateOfPremiere) return false
        if (placeOfPremiere != other.placeOfPremiere) return false
        if (commentary != other.commentary) return false
        if (dedication != other.dedication) return false
        if (instrumentation != other.instrumentation) return false
        if (category != other.category) return false
        if (duration != other.duration) return false
        if (editor != other.editor) return false
        if (relatedText != other.relatedText) return false
        if (discographies != other.discographies) return false
        if (relatedPersons != other.relatedPersons) return false
        if (literatureList != other.literatureList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + dateOfCreation.hashCode()
        result = 31 * result + dateOfPremiere.hashCode()
        result = 31 * result + (placeOfPremiere?.hashCode() ?: 0)
        result = 31 * result + commentary.hashCode()
        result = 31 * result + dedication.hashCode()
        result = 31 * result + instrumentation.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + editor.hashCode()
        result = 31 * result + (relatedText?.hashCode() ?: 0)
        result = 31 * result + discographies.hashCode()
        result = 31 * result + relatedPersons.hashCode()
        result = 31 * result + literatureList.hashCode()
        return result
    }
}