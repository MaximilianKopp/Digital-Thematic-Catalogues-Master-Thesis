package com.ataraxia.gabriel_vz.resource

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

@ApiModel(value = "PlaceResource", description = "Represents a Place")
class PlaceResource(

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
        var coordinates: CoordinatesResource
) : RepresentationModel<PlaceResource>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PlaceResource) return false
        if (!super.equals(other)) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (locality != other.locality) return false
        if (country != other.country) return false
        if (coordinates != other.coordinates) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (self?.hashCode() ?: 0)
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + locality.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + coordinates.hashCode()
        return result
    }
}