package com.ataraxia.gabriel_vz.factory

import arrow.core.Option
import com.ataraxia.gabriel_vz.controller.API.PersonApiController
import com.ataraxia.gabriel_vz.controller.API.WorkApiController
import com.ataraxia.gabriel_vz.model.Person
import com.ataraxia.gabriel_vz.persistence.PersonEntity
import com.ataraxia.gabriel_vz.resource.PersonResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
final class PersonFactory : Factory<Person, PersonEntity, PersonResource>() {

    override fun modelFromEntity(entity: PersonEntity): Person = Person(
            id = entity.id,
            title = entity.title,
            created = entity.created,
            modified = entity.modified,
            description = entity.description,
            role = entity.role,
            name = entity.name,
            pnd = entity.pnd,
            relatedWorks = emptyMap(),
            relatedDiscographies = emptyMap()
    )

    override fun entityFromModel(model: Person): PersonEntity = PersonEntity(
            id = model.id,
            title = model.title,
            created = model.created,
            modified = model.modified,
            description = model.description,
            role = model.role,
            name = model.name,
            pnd = model.pnd
    )

    override fun modelFromResource(resource: PersonResource): Person = Person(
            id = resource.id,
            title = resource.title,
            created = OffsetDateTime.now(),
            modified = OffsetDateTime.now(),
            description = resource.description,
            role = resource.role,
            name = resource.name,
            pnd = resource.pnd,
            relatedWorks = emptyMap(),
            relatedDiscographies = emptyMap()
    )

    override fun resourceFromModel(model: Person): PersonResource = PersonResource(
            self = WebMvcLinkBuilder.linkTo(PersonApiController::class.java)
                    .slash("persons/" + model.id)
                    .withSelfRel()
                    .withTitle(model.title!!),
            collection = WebMvcLinkBuilder.linkTo(PersonApiController::class.java)
                    .slash("persons")
                    .withSelfRel(),
            id = model.id,
            title = model.title,
            created = Option.fromNullable(model.created)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            modified = Option.fromNullable(model.modified)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            description = model.description,
            role = model.role,
            name = model.name,
            pnd = model.pnd,
            relatedWorks = emptyMap(),
            relatedDiscographies = emptyMap()
    )
}