package com.ataraxia.gabriel_vz.factory

import arrow.core.Option
import com.ataraxia.gabriel_vz.controller.API.IncipitApiController
import com.ataraxia.gabriel_vz.model.Incipit
import com.ataraxia.gabriel_vz.persistence.IncipitEntity
import com.ataraxia.gabriel_vz.resource.IncipitResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class IncipitFactory : Factory<Incipit, IncipitEntity, IncipitResource>() {

    override fun modelFromEntity(entity: IncipitEntity): Incipit = Incipit(
            id = entity.id,
            title = entity.title,
            created = entity.created,
            modified = entity.modified,
            text = entity.text,
            clef = entity.clef,
            keysig = entity.keysig,
            timesig = entity.timesig,
            score = entity.score,
            description = entity.description,
            relatedWork = mutableMapOf(Pair(entity.relatedWork?.id, entity.relatedWork?.title))
    )

    override fun entityFromModel(model: Incipit): IncipitEntity = IncipitEntity(
            id = model.id,
            title = model.title,
            created = model.created,
            modified = model.modified,
            text = model.text,
            clef = model.clef,
            keysig = model.keysig,
            timesig = model.timesig,
            score = model.score,
            description = model.description
    )

    override fun modelFromResource(resource: IncipitResource): Incipit = Incipit(
            id = resource.id,
            title = resource.title,
            created = resource.created?.let { OffsetDateTime.parse(it) },
            modified = resource.modified?.let { OffsetDateTime.parse(it) },
            text = resource.text,
            clef = resource.clef,
            keysig = resource.keysig,
            timesig = resource.timesig,
            score = resource.score,
            description = resource.description,
            relatedWork = resource.relatedWork
    )

    override fun resourceFromModel(model: Incipit): IncipitResource = IncipitResource(
            self = WebMvcLinkBuilder.linkTo(IncipitApiController::class.java)
                    .slash("incipits/" + model.id)
                    .withSelfRel()
                    .withTitle(model.title!!),
            collection = WebMvcLinkBuilder.linkTo(IncipitApiController::class.java)
                    .slash("incipits")
                    .withSelfRel(),
            id = model.id,
            title = model.title,
            created = Option.fromNullable(model.created)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            modified = Option.fromNullable(model.modified)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            text = model.text,
            keysig = model.keysig,
            clef = model.clef,
            timesig = model.timesig,
            score = model.score,
            description = model.description,
            relatedWork = model.relatedWork
    )
}