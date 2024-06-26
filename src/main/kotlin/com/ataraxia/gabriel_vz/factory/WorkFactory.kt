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
        val incipitFactory: IncipitFactory,
        val textFactory: TextFactory,
        val placeFactory: PlaceFactory
) : Factory<Work, WorkEntity, WorkResource>() {

    override fun modelFromEntity(entity: WorkEntity): Work = Work(
            id = entity.id,
            title = entity.title,
            opus = entity.opus,
            created = entity.created,
            modified = entity.modified,
            relatedText = entity.relatedText?.let { textFactory.modelFromEntity(it) },
            discographies = entity.discographies?.map(
                    discographyFactory::modelFromEntity
            )!!.toMutableList(),
            instrumentation = entity.instrumentation!!,
            editor = entity.editor!!,
            duration = entity.duration!!,
            placeOfPremiere = entity.placeOfPremiere?.let { placeFactory.modelFromEntity(it) },
            dedication = entity.dedication!!,
            dateOfPremiere = entity.dateOfPremiere!!,
            dateOfCreation = entity.dateOfCreation!!,
            incipit = entity.incipit?.let { incipitFactory.modelFromEntity(it) },
            commentary = entity.commentary!!,
            category = entity.category!!,
            relatedLiterature = entity.literatureList?.map(
                    literatureFactory::modelFromEntity
            )!!.toMutableList(),
            relatedPersons = entity.relatedPersons?.map(
                    personFactory::modelFromEntity
            )!!.toMutableList()
    )

    override fun entityFromModel(model: Work): WorkEntity = WorkEntity(
            id = model.id,
            title = model.title,
            opus = model.opus,
            created = model.created,
            modified = model.modified,
            relatedText = model.relatedText?.let { textFactory.entityFromModel(it) },
            instrumentation = model.instrumentation,
            editor = model.editor,
            duration = model.duration,
            placeOfPremiere = model.placeOfPremiere?.let { placeFactory.entityFromModel(it) },
            dedication = model.dedication,
            dateOfPremiere = model.dateOfPremiere,
            dateOfCreation = model.dateOfCreation,
            incipit = model.incipit?.let { incipitFactory.entityFromModel(it) },
            commentary = model.commentary,
            category = model.category,
            literatureList = model.relatedLiterature?.map(
                    literatureFactory::entityFromModel
            )?.toMutableList(),
            relatedPersons = model.relatedPersons?.map(
                    personFactory::entityFromModel
            )?.toMutableList(),
            discographies = model.discographies?.map(
                    discographyFactory::entityFromModel
            )?.toMutableList()
    )

    override fun modelFromResource(resource: WorkResource): Work = Work(
            id = resource.id,
            title = resource.title,
            opus = resource.opus,
            created = OffsetDateTime.now(),
            modified = OffsetDateTime.now(),
            relatedText = resource.relatedText?.let { textFactory.modelFromResource(it) },
            discographies = resource.discographies.map(
                    discographyFactory::modelFromResource
            ).toMutableList(),
            instrumentation = resource.instrumentation,
            editor = resource.editor,
            duration = resource.duration,
            placeOfPremiere = resource.placeOfPremiere?.let { placeFactory.modelFromResource(it) },
            dedication = resource.dedication,
            dateOfPremiere = resource.dateOfPremiere,
            dateOfCreation = resource.dateOfCreation,
            incipit = resource.incipit?.let { incipitFactory.modelFromResource(it) },
            commentary = resource.commentary,
            category = resource.category,
            relatedLiterature = resource.literatureList.map(
                    literatureFactory::modelFromResource
            ).toMutableList(),
            relatedPersons = resource.relatedPersons.map(
                    personFactory::modelFromResource
            ).toMutableList()
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
            opus = model.opus,
            created = Option.fromNullable(model.created)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            modified = Option.fromNullable(model.modified)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            relatedText = model.relatedText?.let { textFactory.resourceFromModel(it) },
            discographies = model.discographies?.map(
                    discographyFactory::resourceFromModel
            )!!.toMutableSet(),
            instrumentation = model.instrumentation!!,
            editor = model.editor!!,
            duration = model.duration!!,
            placeOfPremiere = model.placeOfPremiere?.let { placeFactory.resourceFromModel(it) },
            dedication = model.dedication!!,
            dateOfPremiere = model.dateOfPremiere!!,
            dateOfCreation = model.dateOfCreation!!,
            incipit = model.incipit?.let { incipitFactory.resourceFromModel(it) },
            commentary = model.commentary!!,
            category = model.category!!,
            literatureList = model.relatedLiterature?.map(
                    literatureFactory::resourceFromModel
            )!!.toMutableSet(),
            relatedPersons = model.relatedPersons?.map(
                    personFactory::resourceFromModel
            )!!.toMutableSet()
    )
}