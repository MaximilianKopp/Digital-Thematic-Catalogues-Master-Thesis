package com.ataraxia.gabriel_vz.factory

import arrow.core.Option
import com.ataraxia.gabriel_vz.controller.API.DiscographyApiController
import com.ataraxia.gabriel_vz.controller.API.WorkApiController
import com.ataraxia.gabriel_vz.model.Discography
import com.ataraxia.gabriel_vz.persistence.DiscographyEntity
import com.ataraxia.gabriel_vz.resource.DiscographyResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class DiscographyFactory : Factory<Discography, DiscographyEntity, DiscographyResource>() {

    val personFactory: PersonFactory = PersonFactory()

    override fun modelFromEntity(entity: DiscographyEntity): Discography = Discography(
            id = entity.id,
            title = entity.title,
            created = entity.created,
            modified = entity.modified,
            dateOfPublishing = entity.dateOfPublishing,
            label = entity.label,
            recordId = entity.recordId,
            musicians = entity.musicians?.map(
                    personFactory::modelFromEntity
            )!!.toMutableSet(),
            relatedWorks = entity.relatedWorks?.map { Pair(it.id, it.title) }!!.toMap()
    )

    override fun entityFromModel(model: Discography): DiscographyEntity = DiscographyEntity(
            id = model.id,
            title = model.title,
            created = model.created,
            modified = model.modified,
            dateOfPublishing = model.dateOfPublishing,
            label = model.label,
            recordId = model.recordId,
            musicians = model.musicians?.map(
                    personFactory::entityFromModel
            )!!.toMutableSet(),
            relatedWorks = mutableSetOf()
    )

    override fun modelFromResource(resource: DiscographyResource): Discography = Discography(
            id = resource.id,
            title = resource.title,
            created = OffsetDateTime.now(),
            modified = OffsetDateTime.now(),
            dateOfPublishing = resource.dateOfPublishing,
            label = resource.label,
            recordId = resource.recordId,
            musicians = resource.musicians?.map(
                    personFactory::modelFromResource
            )!!.toMutableSet(),
            relatedWorks = resource.relatedWorks
    )

    override fun resourceFromModel(model: Discography): DiscographyResource = DiscographyResource(
            self = WebMvcLinkBuilder.linkTo(DiscographyApiController::class.java)
                    .slash("discographies/" + model.id)
                    .withSelfRel()
                    .withTitle(model.title!!),
            collection = WebMvcLinkBuilder.linkTo(DiscographyApiController::class.java)
                    .slash("discographies")
                    .withSelfRel(),
            id = model.id,
            title = model.title,
            created = Option.fromNullable(model.created)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            modified = Option.fromNullable(model.modified)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            dateOfPublishing = model.dateOfPublishing,
            label = model.label,
            recordId = model.recordId,
            musicians = model.musicians?.map(
                    personFactory::resourceFromModel
            )!!.toMutableSet(),
            relatedWorks = model.relatedWorks
    )
}