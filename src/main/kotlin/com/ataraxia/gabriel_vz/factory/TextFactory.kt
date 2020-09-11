package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.controller.API.TextApiController
import com.ataraxia.gabriel_vz.model.Text
import com.ataraxia.gabriel_vz.persistence.TextEntity
import com.ataraxia.gabriel_vz.resource.TextResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class TextFactory : Factory<Text, TextEntity, TextResource>() {
    override fun modelFromEntity(entity: TextEntity): Text = Text(
            id = entity.id,
            title = entity.title,
            author = entity.author,
            excerpt = entity.excerpt
    )

    override fun entityFromModel(model: Text): TextEntity = TextEntity(
            id = model.id,
            title = model.title,
            author = model.author,
            excerpt = model.excerpt
    )

    override fun modelFromResource(resource: TextResource): Text = Text(
            id = resource.id,
            title = resource.title,
            author = resource.author,
            excerpt = resource.excerpt
    )

    override fun resourceFromModel(model: Text): TextResource = TextResource(
            self = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(TextApiController::class.java)
                            .one(model.id!!))
                    .withSelfRel()
                    .withTitle(model.title),
            id = model.id,
            title = model.title,
            author = model.author,
            excerpt = model.excerpt
    )
}