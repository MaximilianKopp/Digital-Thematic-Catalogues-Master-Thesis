package com.ataraxia.gabriel_vz.resource

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

@ApiModel(value = "DiscographyResource", description = "Represents a Discography")
class DiscographyResource(

        @ApiModelProperty(
                notes = "A link to the specific Resource",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY
        )
        var self: Link? = null,
        @ApiModelProperty(
                notes = "A globally unique identifier",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                example = "4028e3817478b2c7017478b2d0250000"
        )
        var id: String?,
        @ApiModelProperty(
                notes = "The title of a Discography",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                dataType = "String",
                example = "Wolfgang Gabriel Collection"
        )
        var title: String,
        @ApiModelProperty(
                notes = "The name of the label",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                dataType = "String",
                example = "Deutsche Gramophon"
        )
        var label: String,
        @ApiModelProperty(
                notes = "The record ID of a published Work",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                dataType = "String",
                example = "928293"
        )
        var recordId: String,
        @ApiModelProperty(
                notes = "Date when the record was published",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                dataType = "String",
                example = "2003-08-12"
        )
        var dateOfPublishing: String,
        @ApiModelProperty(
                notes = "All involved Persons within this performance",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                dataType = "String",
                example = "Hans Werner"
        )
        var musicians: MutableSet<PersonResource>?
) : RepresentationModel<DiscographyResource>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DiscographyResource) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (label != other.label) return false
        if (recordId != other.recordId) return false
        if (dateOfPublishing != other.dateOfPublishing) return false
        if (musicians != other.musicians) return false

        return true
    }

    override fun hashCode(): Int {
        var result = self?.hashCode() ?: 0
        result = 31 * result + id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + recordId.hashCode()
        result = 31 * result + dateOfPublishing.hashCode()
        result = 31 * result + musicians.hashCode()
        return result
    }
}