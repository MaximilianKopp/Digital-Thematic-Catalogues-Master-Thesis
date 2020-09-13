package com.ataraxia.gabriel_vz.resource

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

@ApiModel(value = "PersonResource", description = "Represents a Person")
class PersonResource(

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
                notes = "The name of a Person",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Anna Sailer"
        )
        var name: String,
        @ApiModelProperty(
                notes = "The PND of a Person",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "118601121"
        )
        var pnd: String,
        @ApiModelProperty(
                notes = "The role of a Person",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Conductor"
        )
        var role: String,
        @ApiModelProperty(
                notes = "The title of a Work",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "118601121"
        )
        var description: String,
        @ApiModelProperty(
                notes = "The list of related Works",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Sonata C-Dur"
        )
        var relatedWorks: MutableSet<WorkResource>? = mutableSetOf(),
        @ApiModelProperty(
                notes = "The list of related Discographies",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Wolfgang Gabriel - All Violin-Sonatas"
        )
        var relatedDiscographies: MutableSet<DiscographyResource>? = mutableSetOf()
) : RepresentationModel<PersonResource>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersonResource) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (pnd != other.pnd) return false
        if (role != other.role) return false
        if (description != other.description) return false
        if (relatedWorks != other.relatedWorks) return false
        if (relatedDiscographies != other.relatedDiscographies) return false

        return true
    }

    override fun hashCode(): Int {
        var result = self?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + pnd.hashCode()
        result = 31 * result + role.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + relatedWorks.hashCode()
        result = 31 * result + relatedDiscographies.hashCode()
        return result
    }
}