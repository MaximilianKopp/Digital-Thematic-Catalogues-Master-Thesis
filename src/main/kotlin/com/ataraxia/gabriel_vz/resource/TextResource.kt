package com.ataraxia.gabriel_vz.resource

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

@ApiModel(value = "TextResource", description = "Represents a Text")
class TextResource(
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
                notes = "The title of a Text",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Nine little Cats"
        )
        var title: String,
        @ApiModelProperty(
                notes = "The author of this text",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Christoph van Cat"
        )
        var author: String,
        @ApiModelProperty(
                notes = "An excerpt of the text",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Nine little cats are tiny little brags"
        )
        var excerpt: String
) : RepresentationModel<TextResource>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TextResource) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (author != other.author) return false
        if (excerpt != other.excerpt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = self?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + title.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + excerpt.hashCode()
        return result
    }
}