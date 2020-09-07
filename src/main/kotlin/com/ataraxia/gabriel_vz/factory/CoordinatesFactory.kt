package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Coordinates
import com.ataraxia.gabriel_vz.persistence.CoordinatesEntity
import com.ataraxia.gabriel_vz.resource.CoordinatesResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.stereotype.Component

@Component
class CoordinatesFactory : Factory<Coordinates, CoordinatesEntity, CoordinatesResource>() {

    override fun modelFromEntity(entity: CoordinatesEntity): Coordinates = Coordinates(
            longitude = entity.longitude,
            latitude = entity.latitude
    )

    override fun entityFromModel(model: Coordinates): CoordinatesEntity = CoordinatesEntity(
            longitude = model.longitude,
            latitude = model.latitude
    )

    override fun modelFromResource(resource: CoordinatesResource): Coordinates = Coordinates(
            longitude = resource.longitude,
            latitude = resource.latitude
    )

    override fun resourceFromModel(model: Coordinates): CoordinatesResource = CoordinatesResource(
            self = null,
            longitude = model.longitude,
            latitude = model.latitude
    )
}