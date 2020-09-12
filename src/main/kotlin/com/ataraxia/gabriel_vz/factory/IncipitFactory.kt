package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Incipit
import com.ataraxia.gabriel_vz.persistence.IncipitEntity
import com.ataraxia.gabriel_vz.resource.IncipitResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.stereotype.Component

@Component
class IncipitFactory : Factory<Incipit, IncipitEntity, IncipitResource>() {

    override fun modelFromEntity(entity: IncipitEntity): Incipit = Incipit(
            id = entity.id,
            text = entity.text,
            keysig = entity.keysig,
            timesig = entity.timesig,
            score = entity.score,
            description = entity.description
    )

    override fun entityFromModel(model: Incipit): IncipitEntity = IncipitEntity(
            id = model.id,
            text = model.text,
            keysig = model.keysig,
            timesig = model.timesig,
            score = model.score,
            description = model.description
    )

    override fun modelFromResource(resource: IncipitResource): Incipit = Incipit(
            id = resource.id,
            text = resource.text,
            keysig = resource.keysig,
            timesig = resource.timesig,
            score = resource.score,
            description = resource.description
    )

    override fun resourceFromModel(model: Incipit): IncipitResource = IncipitResource(
            self = null,
            id = model.id,
            text = model.text,
            keysig = model.keysig,
            timesig = model.timesig,
            score = model.score,
            description = model.description
    )
}