package com.ataraxia.gabriel_vz.factory

import arrow.core.Option
import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.persistence.LiteratureEntity
import com.ataraxia.gabriel_vz.resource.LiteratureResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class LiteratureFactory : Factory<Literature, LiteratureEntity, LiteratureResource>() {
    override fun modelFromEntity(entity: LiteratureEntity): Literature = Literature(
            id = entity.id,
            title = entity.title,
            created = entity.created,
            modified = entity.modified,
            yearOfPublishing = entity.yearOfPublishing,
            isbn = entity.isbn,
            author = entity.author
    )

    override fun entityFromModel(model: Literature): LiteratureEntity = LiteratureEntity(
            id = model.id,
            title = model.title,
            created = model.created,
            modified = model.modified,
            yearOfPublishing = model.yearOfPublishing,
            isbn = model.isbn,
            author = model.author
    )

    override fun modelFromResource(resource: LiteratureResource): Literature = Literature(
            id = resource.id,
            title = resource.title,
            created = resource.created?.let { OffsetDateTime.parse(it) },
            modified = resource.modified?.let { OffsetDateTime.parse(it) },
            yearOfPublishing = resource.yearOfPublishing,
            isbn = resource.isbn,
            author = resource.author
    )

    override fun resourceFromModel(model: Literature): LiteratureResource = LiteratureResource(
            self = null,
            id = model.id,
            title = model.title,
            created = Option.fromNullable(model.created)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            modified = Option.fromNullable(model.modified)
                    .map(OffsetDateTime::toString)
                    .orNull(),
            yearOfPublishing = model.yearOfPublishing,
            isbn = model.isbn,
            author = model.author
    )
}