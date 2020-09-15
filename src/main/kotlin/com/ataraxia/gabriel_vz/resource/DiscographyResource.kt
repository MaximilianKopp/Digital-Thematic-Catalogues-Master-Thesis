package com.ataraxia.gabriel_vz.resource

import com.ataraxia.gabriel_vz.root.Resource
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.Link
import org.springframework.http.CacheControl

import org.springframework.http.MediaType
import java.util.concurrent.TimeUnit

@ApiModel(value = "DiscographyResource", description = "Represents a Discography")
class DiscographyResource(
        self: Link?,
        id: String?,
        title: String?,
        created: String?,
        modified: String?,
        @ApiModelProperty(
                notes = "The name of the label",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                dataType = "String",
                example = "Deutsche Gramophon"
        )
        var label: String?,

        @ApiModelProperty(
                notes = "The record ID of a published Work",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                dataType = "String",
                example = "928293"
        )
        var recordId: String?,

        @ApiModelProperty(
                notes = "Date when the record was published",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                dataType = "String",
                example = "2003-08-12"
        )
        var dateOfPublishing: String?,

        @ApiModelProperty(
                notes = "All involved Persons within this performance",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                dataType = "String",
                example = "Hans Werner"
        )
        var musicians: MutableSet<PersonResource>?
) : Resource(
        self,
        id,
        title,
        created,
        modified
) {
    override fun cacheControl(): CacheControl = CacheControl.maxAge(5, TimeUnit.MINUTES)
    override fun contentType(): MediaType = MediaType("application", "com.ataraxia.gabriel-catalogue+json")
    override fun eTag(): String = hashCode().toString()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DiscographyResource) return false
        if (!super.equals(other)) return false

        if (label != other.label) return false
        if (recordId != other.recordId) return false
        if (dateOfPublishing != other.dateOfPublishing) return false
        if (musicians != other.musicians) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + (recordId?.hashCode() ?: 0)
        result = 31 * result + (dateOfPublishing?.hashCode() ?: 0)
        result = 31 * result + (musicians?.hashCode() ?: 0)
        return result
    }


}