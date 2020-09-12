package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.persistence.LiteratureEntity
import com.ataraxia.gabriel_vz.resource.LiteratureResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.stereotype.Component

@Component
class LiteratureFactory : Factory<Literature, LiteratureEntity, LiteratureResource>() {
    override fun modelFromEntity(entity: LiteratureEntity): Literature = Literature(
            id = entity.id,
            yearOfPublishing = entity.yearOfPublishing,
            isbn = entity.isbn,
            author = entity.author
    )

    override fun entityFromModel(model: Literature): LiteratureEntity = LiteratureEntity(
            id = model.id,
            yearOfPublishing = model.yearOfPublishing,
            isbn = model.isbn,
            author = model.author
    )

    override fun modelFromResource(resource: LiteratureResource): Literature = Literature(
            id = resource.id,
            yearOfPublishing = resource.yearOfPublishing,
            isbn = resource.isbn,
            author = resource.author
    )

    override fun resourceFromModel(model: Literature): LiteratureResource = LiteratureResource(
            self = null,
            id = model.id,
            yearOfPublishing = model.yearOfPublishing,
            isbn = model.isbn,
            author = model.author
    )
}