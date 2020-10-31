package com.ataraxia.gabriel_vz.factory

import arrow.core.Option
import com.ataraxia.gabriel_vz.controller.API.TextApiController
import com.ataraxia.gabriel_vz.model.Text
import com.ataraxia.gabriel_vz.persistence.TextEntity
import com.ataraxia.gabriel_vz.resource.TextResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class TextFactory : Factory<Text, TextEntity, TextResource>() {
    override fun modelFromEntity(entity: TextEntity): Text = Text(
            id = entity.id,
            title = entity.title,
            created = entity.created,
            modified = entity.modified,
            author = entity.author,
            excerpt = entity.excerpt,
            relatedWorks = entity.relatedWorks?.map { Pair(it.id, it.title) }!!.toMap()
    )

    override fun entityFromModel(model: Text): TextEntity = TextEntity(
            id = model.id,
            title = model.title,
            created = model.created,
            modified = model.modified,
            author = model.author,
            excerpt = model.excerpt
    )

    override fun modelFromResource(resource: TextResource): Text = Text(
            id = resource.id,
            title = resource.title,
            created = OffsetDateTime.now(),
            modified = OffsetDateTime.now(),
            author = resource.author,
            excerpt = resource.excerpt
    )

    override fun resourceFromModel(model: Text): TextResource = TextResource(
            self = WebMvcLinkBuilder.linkTo(TextApiController::class.java)
                    .slash("texts/" + model.id)
                    .withSelfRel()
                    .withTitle(model.title!!),
            collection = WebMvcLinkBuilder.linkTo(TextApiController::class.java)
                    .slash("texts")
                    .withSelfRel(),
            id = model.id,
            title = model.title,
            created = Option.fromNullable(model.created)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            modified = Option.fromNullable(model.modified)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            author = model.author,
            excerpt = model.excerpt
    )
}