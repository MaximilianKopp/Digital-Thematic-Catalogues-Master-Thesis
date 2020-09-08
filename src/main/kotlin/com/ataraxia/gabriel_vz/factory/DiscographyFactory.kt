package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Discography
import com.ataraxia.gabriel_vz.persistence.DiscographyEntity
import com.ataraxia.gabriel_vz.resource.DiscographyResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.stereotype.Component

@Component
class DiscographyFactory(

) : Factory<Discography, DiscographyEntity, DiscographyResource>() {

    val personFactory: PersonFactory = PersonFactory()

    override fun modelFromEntity(entity: DiscographyEntity): Discography = Discography(
            id = entity.id,
            title = entity.title,
            dateOfPublishing = entity.dateOfPublishing,
            label = entity.label,
            recordId = entity.recordId,
            musicians = entity.musicians?.map(
                    personFactory::modelFromEntity
            )!!.toMutableSet()
    )


    override fun entityFromModel(model: Discography): DiscographyEntity = DiscographyEntity(
            id = model.id,
            title = model.title,
            dateOfPublishing = model.dateOfPublishing,
            label = model.label,
            recordId = model.recordId,
            musicians = model.musicians?.map(
                    personFactory::entityFromModel
            )!!.toMutableSet()
    )

    override fun modelFromResource(resource: DiscographyResource): Discography = Discography(
            id = resource.id,
            title = resource.title,
            dateOfPublishing = resource.dateOfPublishing,
            label = resource.label,
            recordId = resource.recordId,
            musicians = resource.musicians?.map(
                    personFactory::modelFromResource
            )!!.toMutableSet()
    )

    override fun resourceFromModel(model: Discography): DiscographyResource = DiscographyResource(
            self = null,
            id = model.id,
            title = model.title,
            dateOfPublishing = model.dateOfPublishing,
            label = model.label,
            recordId = model.recordId,
            musicians = model.musicians?.map(
                    personFactory::resourceFromModel
            )!!.toMutableSet()
    )
}