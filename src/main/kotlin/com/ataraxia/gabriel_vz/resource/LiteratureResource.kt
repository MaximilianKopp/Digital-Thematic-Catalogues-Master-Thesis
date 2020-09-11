package com.ataraxia.gabriel_vz.resource

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

@ApiModel(value = "LiteratureResource", description = "Represents a Literature")
class LiteratureResource(

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
                notes = "Name of author",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Georgina van Heelen"
        )
        var author: String,
        @ApiModelProperty(
                notes = "The given ISBN code",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "978-3-16-148410-0"
        )
        var isbn: String,
        @ApiModelProperty(
                notes = "Year of Publication",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "2010-06-12"
        )
        var yearOfPublishing: String
) : RepresentationModel<LiteratureResource>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LiteratureResource) return false

        if (id != other.id) return false
        if (author != other.author) return false
        if (isbn != other.isbn) return false
        if (yearOfPublishing != other.yearOfPublishing) return false

        return true
    }

    override fun hashCode(): Int {
        var result = self?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + author.hashCode()
        result = 31 * result + isbn.hashCode()
        result = 31 * result + yearOfPublishing.hashCode()
        return result
    }
}