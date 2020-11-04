package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.Model
import java.time.OffsetDateTime

class Work(
        id: String? = null,
        title: String? = null,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var opus: String? = null,
        var dateOfCreation: String? = null,
        var dateOfPremiere: String? = null,
        var placeOfPremiere: Place? = Place(),
        var incipit: Incipit? = null,
        var commentary: String? = null,
        var dedication: String? = null,
        var instrumentation: String? = null,
        var category: String? = null,
        var duration: String? = null,
        var editor: String? = null,
        var relatedText: Text? = null,
        var discographies: MutableSet<Discography>? = mutableSetOf(),
        var relatedPersons: MutableSet<Person>? = mutableSetOf(),
        var literatureList: MutableSet<Literature>? = mutableSetOf()
) : Model(
        id,
        title,
        created,
        modified
) {

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

    override fun toString(): String {
        return "Work(opus=$opus, dateOfCreation=$dateOfCreation, dateOfPremiere=$dateOfPremiere, placeOfPremiere=$placeOfPremiere, incipit=$incipit, commentary=$commentary, dedication=$dedication, instrumentation=$instrumentation, category=$category, duration=$duration, editor=$editor, relatedText=$relatedText, discographies=$discographies, relatedPersons=$relatedPersons, literatureList=$literatureList)"
    }
}