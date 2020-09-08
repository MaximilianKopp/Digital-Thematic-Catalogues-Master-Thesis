package com.ataraxia.gabriel_vz.resource

import com.ataraxia.gabriel_vz.model.*
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

class WorkResource(
        var self: Link?,
        var id: String?,
        var title: String,
        var dateOfCreation: String,
        var dateOfPremiere: String,
        var placeOfPremiere: PlaceResource?,
        var commentary: String,
        var dedication: String,
        var instrumentation: String,
        var category: String,
        var duration: String,
        var editor: String,
        var relatedText: TextResource?,
        var discographies: MutableSet<DiscographyResource>,
        var relatedPersons: MutableSet<PersonResource>,
        var literatureList: MutableSet<LiteratureResource>
) : RepresentationModel<WorkResource>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WorkResource) return false

        if (id != other.id) return false
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
        var result = id?.hashCode() ?: 0
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