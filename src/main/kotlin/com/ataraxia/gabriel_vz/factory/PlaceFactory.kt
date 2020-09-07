package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Place
import com.ataraxia.gabriel_vz.persistence.PlaceEntity
import com.ataraxia.gabriel_vz.resource.PlaceResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PlaceFactory : Factory<Place, PlaceEntity, PlaceResource>() {

    private val coordinatesFactory: CoordinatesFactory = CoordinatesFactory()

    override fun modelFromEntity(entity: PlaceEntity): Place = Place(
            id = entity.id,
            name = entity.name,
            coordinates = coordinatesFactory.modelFromEntity(entity.coordinates),
            country = entity.country,
            locality = entity.locality
    )

    override fun entityFromModel(model: Place): PlaceEntity = PlaceEntity(
            id = model.id,
            name = model.name,
            coordinates = coordinatesFactory.entityFromModel(model.coordinates),
            country = model.country,
            locality = model.locality
    )

    override fun modelFromResource(resource: PlaceResource): Place = Place(
            id = resource.id,
            name = resource.name,
            coordinates = coordinatesFactory.modelFromResource(resource.coordinates),
            country = resource.country,
            locality = resource.locality
    )

    override fun resourceFromModel(model: Place): PlaceResource = PlaceResource(
            self = null,
            id = model.id,
            name = model.name,
            coordinates = coordinatesFactory.resourceFromModel(model.coordinates),
            country = model.country,
            locality = model.locality
    )
}