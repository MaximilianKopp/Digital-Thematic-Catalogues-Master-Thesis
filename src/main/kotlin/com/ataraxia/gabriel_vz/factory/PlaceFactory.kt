package com.ataraxia.gabriel_vz.factory

import arrow.core.Option
import com.ataraxia.gabriel_vz.model.Place
import com.ataraxia.gabriel_vz.persistence.PlaceEntity
import com.ataraxia.gabriel_vz.resource.PlaceResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class PlaceFactory(
        val coordinatesFactory: CoordinatesFactory
) : Factory<Place, PlaceEntity, PlaceResource>() {


    override fun modelFromEntity(entity: PlaceEntity): Place = Place(
            id = entity.id,
            title = entity.title,
            created = entity.created,
            modified = entity.modified,
            name = entity.name,
            coordinates = coordinatesFactory.modelFromEntity(entity.coordinates),
            country = entity.country,
            locality = entity.locality
    )

    override fun entityFromModel(model: Place): PlaceEntity = PlaceEntity(
            id = model.id,
            title = model.title,
            created = model.created,
            modified = model.modified,
            name = model.name,
            coordinates = coordinatesFactory.entityFromModel(model.coordinates),
            country = model.country,
            locality = model.locality
    )

    override fun modelFromResource(resource: PlaceResource): Place = Place(
            id = resource.id,
            title = resource.title,
            created = resource.created?.let { OffsetDateTime.parse(it) },
            modified = resource.modified?.let { OffsetDateTime.parse(it) },
            name = resource.name,
            coordinates = coordinatesFactory.modelFromResource(resource.coordinates),
            country = resource.country,
            locality = resource.locality
    )

    override fun resourceFromModel(model: Place): PlaceResource = PlaceResource(
            self = null,
            id = model.id,
            title = model.title,
            created = Option.fromNullable(model.created)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            modified = Option.fromNullable(model.modified)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            name = model.name,
            coordinates = coordinatesFactory.resourceFromModel(model.coordinates),
            country = model.country,
            locality = model.locality
    )
}