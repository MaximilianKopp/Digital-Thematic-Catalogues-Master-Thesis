package com.ataraxia.gabriel_vz.resource

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

@ApiModel(value = "WorkResource", description = "Represents a Work")
class WorkResource(

        @ApiModelProperty(
                notes = "A link to the specific Resource",
                accessMode = READ_ONLY
        )
        var self: Link?,
        @ApiModelProperty(
                notes = "A globally unique identifier",
                accessMode = READ_ONLY,
                example = "4028e3817478b2c7017478b2d0250000"
        )
        var id: String?,
        @ApiModelProperty(
                notes = "The title of a Work",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Sonata in C-Dur"
        )
        var title: String,
        @ApiModelProperty(
                notes = "Date when the Work was created",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Sonata in C-Dur"
        )
        var dateOfCreation: String,
        @ApiModelProperty(
                notes = "Date of the first performance",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "2015-09-21"
        )
        var dateOfPremiere: String,
        @ApiModelProperty(
                notes = "Place of the first performance",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "2015-09-21"
        )
        var placeOfPremiere: PlaceResource?,
        @ApiModelProperty(
                notes = "Further information about a Work",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Gabriel wrote this work in his early period"
        )
        var commentary: String,
        @ApiModelProperty(
                notes = "Mentions the person to whom this work is dedicated",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "For Mrs. Holly"
        )
        var dedication: String,
        @ApiModelProperty(
                notes = "Describes the formation of instruments",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Piano, Violin I, Violin II and Percussions"
        )
        var instrumentation: String,
        @ApiModelProperty(
                notes = "Defines the genre of this piece",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Piano music"
        )
        var category: String,
        @ApiModelProperty(
                notes = "Defines the duration of a piece",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "33 Minutes"
        )
        var duration: String,
        @ApiModelProperty(
                notes = "Name of the current editor",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Luisa"
        )
        var editor: String,
        @ApiModelProperty(
                notes = "Text template of a vocal piece",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "The nine cats"
        )
        var relatedText: TextResource?,
        @ApiModelProperty(
                notes = "Set of recorded pieces",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Wolfgang Gabriel- Complete Piano Music"
        )
        var discographies: MutableSet<DiscographyResource>,
        @ApiModelProperty(
                notes = "Involved persons",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Hans Schauer"
        )
        var relatedPersons: MutableSet<PersonResource>,
        @ApiModelProperty(
                notes = "Related literature and references of this piece",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Susanne Hübner - Wolfgang Gabriel Leben und Werk"
        )
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