package com.ataraxia.gabriel_vz.resource

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

@ApiModel(value = "IncipitResource", description = "Represents a Incipit")
class IncipitResource(

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
                notes = "Text templace of a Song e.g",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "The nine cats are brats.."
        )
        var text: String,
        @ApiModelProperty(
                notes = "The key of a piece",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "G"
        )
        var keysig: String,
        @ApiModelProperty(
                notes = "The timesignature of a piece",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "2/4"
        )
        var timesig: String,
        @ApiModelProperty(
                notes = "The incipit of a piece (written in Plaine & Easie Code",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "f-a-c-d-e"
        )
        var score: String,
        @ApiModelProperty(
                notes = "Further Information of this piece",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "This work was written in Gabriels early period"
        )
        var description: String
) : RepresentationModel<IncipitResource>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IncipitResource) return false
        if (!super.equals(other)) return false

        if (id != other.id) return false
        if (text != other.text) return false
        if (keysig != other.keysig) return false
        if (timesig != other.timesig) return false
        if (score != other.score) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (self?.hashCode() ?: 0)
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + text.hashCode()
        result = 31 * result + keysig.hashCode()
        result = 31 * result + timesig.hashCode()
        result = 31 * result + score.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}