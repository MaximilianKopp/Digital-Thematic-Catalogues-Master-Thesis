package com.ataraxia.gabriel_vz.root

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.Link
import org.springframework.http.CacheControl
import org.springframework.http.MediaType

/**
 * A basic Resource implementation.
 *
 * A Resource is an object a client can interact with through Links and HTTP-methods.
 *
 * @property self a Link to this very Resource.
 * @property id a globally unique identifier.
 * @property title the title of this Resource.
 * @property created a UTC date of the underlying [Model] of this Resource was created.
 * @property modified the most recent UTC date of the underlying [Model] of this Resource was modified,
 * equals [created] if no modifications did occur.
 * */
@ApiModel(value = "Resource", description = "Represents a Resource")
abstract class Resource(

        @ApiModelProperty(
                notes = "A Link to this very Resource"
        )
        open val self: Link?,
        @ApiModelProperty(
                notes = "A globally unique identifier.",
                example = "urn:ard:examples:123e4567-e89b-12d3-a456-426655440000",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY
        )
        open val id: String?,
        @ApiModelProperty(
                notes = "The title of this resource.",
                example = "A very nice title",
                required = true,
                accessMode = ApiModelProperty.AccessMode.READ_WRITE
        )
        open val title: String?,
        @ApiModelProperty(
                notes = "A UTC DateTime string this resource was created.",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                example = "2019-01-20T20:12:11.2389491Z"
        )
        open val created: String?,
        @ApiModelProperty(
                notes = "The most recent UTC DateTime this resource was modified, equals created if no modification did occur",
                accessMode = ApiModelProperty.AccessMode.READ_ONLY,
                example = "2019-01-20T20:12:11.2389491Z"
        )
        open val modified: String?
) {

    /**
     * Abstract method for CacheControl header an implementations must provide.
     * */
    abstract fun cacheControl(): CacheControl

    /**
     * Abstract method for the MediaType an implementations must provide.
     * */
    abstract fun contentType(): MediaType

    /**
     * Abstract method for the eTag an implementations must provide.
     * */
    abstract fun eTag(): String
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Resource) return false

        //if (self != other.self) return false
        if (id != other.id) return false
        if (title != other.title) return false
        if (created != other.created) return false
        if (modified != other.modified) return false

        return true
    }

    override fun hashCode(): Int {
        var result = self?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (created?.hashCode() ?: 0)
        result = 31 * result + (modified?.hashCode() ?: 0)
        return result
    }


}