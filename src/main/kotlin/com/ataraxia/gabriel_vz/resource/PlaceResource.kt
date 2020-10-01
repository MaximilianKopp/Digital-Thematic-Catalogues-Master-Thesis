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

@ApiModel(value = "PlaceResource", description = "Represents a Place")
class PlaceResource(
        self: Link?,
        collection: Link?,
        id: String?,
        title: String?,
        created: String?,
        modified: String?,
        @ApiModelProperty(
                notes = "Name of this Place",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Vienna"
        )
        var name: String,
        @ApiModelProperty(
                notes = "Further description of a Place",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Recording Studio of the Austrian Broadcasting Corporation "
        )
        var locality: String,
        @ApiModelProperty(
                notes = "The country of a given Place",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Austria"
        )
        var country: String,
        @ApiModelProperty(
                notes = "GPS-Coordinates of a Place",
                accessMode = READ_ONLY,
                dataType = "String",
                example = "Longitude: 33.22, Latitude: 22.13"
        )
        var coordinates: CoordinatesResource,

        @ApiModelProperty(
                notes = "Related Works",
                accessMode = READ_ONLY,
                dataType = "String"
        )
        var relatedWorks: Map<String?, String?>
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
        if (other !is PlaceResource) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (locality != other.locality) return false
        if (country != other.country) return false
        if (coordinates != other.coordinates) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + locality.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + coordinates.hashCode()
        return result
    }
}