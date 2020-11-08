package com.ataraxia.gabriel_vz.resource

import com.ataraxia.gabriel_vz.root.Resource
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY
import org.springframework.hateoas.Link
import org.springframework.http.CacheControl
import org.springframework.http.MediaType
import java.util.concurrent.TimeUnit

@ApiModel(value = "LiteratureResource", description = "Represents a Literature")
class LiteratureResource(
        self: Link?,
        collection: Link?,
        id: String?,
        title: String?,
        created: String?,
        modified: String?,
        @ApiModelProperty(
                notes = "Name of author",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Georgina van Heelen"
        )
        var author: String?,
        @ApiModelProperty(
                notes = "The given ISBN code",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "978-3-16-148410-0"
        )
        var isbn: String?,
        @ApiModelProperty(
                notes = "Year of Publication",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "2010-06-12"
        )
        var yearOfPublishing: String?,
        @ApiModelProperty(
                notes = "Related Work",
                accessMode = READ_ONLY,
                dataType = "String"
        )
        var relatedWork: Map<String?, String?>

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
        if (other !is LiteratureResource) return false
        if (!super.equals(other)) return false

        if (author != other.author) return false
        if (isbn != other.isbn) return false
        if (yearOfPublishing != other.yearOfPublishing) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + isbn.hashCode()
        result = 31 * result + yearOfPublishing.hashCode()
        return result
    }

}