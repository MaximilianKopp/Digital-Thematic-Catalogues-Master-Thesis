package com.ataraxia.gabriel_vz.factory

import arrow.core.Option
import com.ataraxia.gabriel_vz.controller.API.WorkApiController
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.persistence.WorkEntity
import com.ataraxia.gabriel_vz.resource.WorkResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class WorkFactory(
        val discographyFactory: DiscographyFactory,
        val literatureFactory: LiteratureFactory,
        val personFactory: PersonFactory,
        val textFactory: TextFactory,
        val placeFactory: PlaceFactory
) : Factory<Work, WorkEntity, WorkResource>() {

    override fun modelFromEntity(entity: WorkEntity): Work = Work(
            id = entity.id,
            title = entity.title,
            created = entity.created,
            modified = entity.modified,
            relatedText = entity.relatedText?.let { textFactory.modelFromEntity(it) },
            discographies = entity.discographies.map(
                    discographyFactory::modelFromEntity
            ).toMutableSet(),
            instrumentation = entity.instrumentation,
            editor = entity.editor,
            duration = entity.duration,
            placeOfPremiere = entity.placeOfPremiere?.let { placeFactory.modelFromEntity(it) },
            dedication = entity.dedication,
            dateOfPremiere = entity.dateOfPremiere,
            dateOfCreation = entity.dateOfCreation,
            commentary = entity.commentary,
            category = entity.category,
            literatureList = entity.literatureList?.map(
                    literatureFactory::modelFromEntity
            )!!.toMutableSet(),
            relatedPersons = entity.relatedPersons?.map(
                    personFactory::modelFromEntity
            )!!.toMutableSet()
    )

    override fun entityFromModel(model: Work): WorkEntity = WorkEntity(
            id = model.id,
            title = model.title,
            created = model.created,
            modified = model.modified,
            relatedText = model.relatedText?.let { textFactory.entityFromModel(it) },
            discographies = model.discographies.map(
                    discographyFactory::entityFromModel
            ).toMutableSet(),
            instrumentation = model.instrumentation,
            editor = model.editor,
            duration = model.duration,
            placeOfPremiere = model.placeOfPremiere?.let { placeFactory.entityFromModel(it) },
            dedication = model.dedication,
            dateOfPremiere = model.dateOfPremiere,
            dateOfCreation = model.dateOfCreation,
            commentary = model.commentary,
            category = model.category,
            literatureList = model.literatureList.map(
                    literatureFactory::entityFromModel
            ).toMutableSet(),
            relatedPersons = model.relatedPersons.map(
                    personFactory::entityFromModel
            ).toMutableSet()
    )

    override fun modelFromResource(resource: WorkResource): Work = Work(
            id = resource.id,
            title = resource.title,
            created = resource.created?.let { OffsetDateTime.parse(it) },
            modified = resource.modified?.let { OffsetDateTime.parse(it) },
            relatedText = resource.relatedText?.let { textFactory.modelFromResource(it) },
            discographies = resource.discographies.map(
                    discographyFactory::modelFromResource
            ).toMutableSet(),
            instrumentation = resource.instrumentation,
            editor = resource.editor,
            duration = resource.duration,
            placeOfPremiere = resource.placeOfPremiere?.let { placeFactory.modelFromResource(it) },
            dedication = resource.dedication,
            dateOfPremiere = resource.dateOfPremiere,
            dateOfCreation = resource.dateOfCreation,
            commentary = resource.commentary,
            category = resource.category,
            literatureList = resource.literatureList.map(
                    literatureFactory::modelFromResource
            ).toMutableSet(),
            relatedPersons = resource.relatedPersons.map(
                    personFactory::modelFromResource
            ).toMutableSet()
    )

    override fun resourceFromModel(model: Work): WorkResource = WorkResource(
            self = WebMvcLinkBuilder.linkTo(WorkApiController::class.java)
                    .slash("works/" + model.id)
                    .withSelfRel()
                    .withTitle(model.title!!),
            collection = WebMvcLinkBuilder.linkTo(WorkApiController::class.java)
                    .slash("works")
                    .withSelfRel(),
            id = model.id,
            title = model.title,
            created = Option.fromNullable(model.created)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            modified = Option.fromNullable(model.modified)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            relatedText = model.relatedText?.let { textFactory.resourceFromModel(it) },
            discographies = model.discographies.map(
                    discographyFactory::resourceFromModel
            ).toMutableSet(),
            instrumentation = model.instrumentation,
            editor = model.editor,
            duration = model.duration,
            placeOfPremiere = model.placeOfPremiere?.let { placeFactory.resourceFromModel(it) },
            dedication = model.dedication,
            dateOfPremiere = model.dateOfPremiere,
            dateOfCreation = model.dateOfCreation,
            commentary = model.commentary,
            category = model.category,
            literatureList = model.literatureList.map(
                    literatureFactory::resourceFromModel
            ).toMutableSet(),
            relatedPersons = model.relatedPersons.map(
                    personFactory::resourceFromModel
            ).toMutableSet()
    )
}