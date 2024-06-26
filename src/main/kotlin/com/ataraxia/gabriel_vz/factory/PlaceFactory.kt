package com.ataraxia.gabriel_vz.factory

import arrow.core.Option
import com.ataraxia.gabriel_vz.controller.API.PlaceApiController
import com.ataraxia.gabriel_vz.controller.API.WorkApiController
import com.ataraxia.gabriel_vz.model.Place
import com.ataraxia.gabriel_vz.persistence.PlaceEntity
import com.ataraxia.gabriel_vz.resource.PlaceResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
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
            coordinates = entity.coordinates?.let { coordinatesFactory.modelFromEntity(it) },
            country = entity.country,
            locality = entity.locality,
            relatedWorks = entity.relatedWorks?.map { Pair(it.id, it.title) }!!.toMap()
    )

    override fun entityFromModel(model: Place): PlaceEntity = PlaceEntity(
            id = model.id,
            title = model.title,
            created = model.created,
            modified = model.modified,
            name = model.name,
            coordinates = model.coordinates?.let { coordinatesFactory.entityFromModel(it) },
            country = model.country,
            locality = model.locality
    )

    override fun modelFromResource(resource: PlaceResource): Place = Place(
            id = resource.id,
            title = resource.title,
            created = OffsetDateTime.now(),
            modified = OffsetDateTime.now(),
            name = resource.name,
            coordinates = coordinatesFactory.modelFromResource(resource.coordinates),
            country = resource.country,
            locality = resource.locality
    )

    override fun resourceFromModel(model: Place): PlaceResource = PlaceResource(
            self = WebMvcLinkBuilder.linkTo(PlaceApiController::class.java)
                    .slash("places/" + model.id)
                    .withSelfRel()
                    .withTitle(model.title!!),
            collection = WebMvcLinkBuilder.linkTo(PlaceApiController::class.java)
                    .slash("places")
                    .withSelfRel(),
            id = model.id,
            title = model.title,
            created = Option.fromNullable(model.created)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            modified = Option.fromNullable(model.modified)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            name = model.name!!,
            coordinates = coordinatesFactory.resourceFromModel(model.coordinates!!),
            country = model.country!!,
            locality = model.locality!!,
            relatedWorks = model.relatedWorks
    )
}