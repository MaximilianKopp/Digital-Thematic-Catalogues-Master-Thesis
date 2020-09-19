package com.ataraxia.gabriel_vz.resource

import com.ataraxia.gabriel_vz.root.Resource
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import org.springframework.http.CacheControl
import org.springframework.http.MediaType
import java.util.concurrent.TimeUnit

@ApiModel(value = "TextResource", description = "Represents a Text")
class TextResource(
        self: Link?,
        collection: Link?,
        id: String?,
        title: String?,
        created: String?,
        modified: String?,
        @ApiModelProperty(
                notes = "The author of this text",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Christoph van Cat"
        )
        var author: String?,
        @ApiModelProperty(
                notes = "An excerpt of the text",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Nine little cats are tiny little brags"
        )
        var excerpt: String?
) : Resource(
        self,
        collection,
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
        if (other !is TextResource) return false
        if (!super.equals(other)) return false

        if (author != other.author) return false
        if (excerpt != other.excerpt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + excerpt.hashCode()
        return result
    }

}