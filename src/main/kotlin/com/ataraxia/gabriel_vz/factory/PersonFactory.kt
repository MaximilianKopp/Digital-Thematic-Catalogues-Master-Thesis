package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Person
import com.ataraxia.gabriel_vz.persistence.PersonEntity
import com.ataraxia.gabriel_vz.resource.PersonResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.stereotype.Component

@Component
final class PersonFactory : Factory<Person, PersonEntity, PersonResource>() {

    override fun modelFromEntity(entity: PersonEntity): Person = Person(
            id = entity.id,
            description = entity.description,
            role = entity.role,
            name = entity.name,
            pnd = entity.pnd

    )

    override fun entityFromModel(model: Person): PersonEntity = PersonEntity(
            id = model.id,
            description = model.description,
            role = model.role,
            name = model.name,
            pnd = model.pnd
    )

    override fun modelFromResource(resource: PersonResource): Person = Person(
            id = resource.id,
            description = resource.description,
            role = resource.role,
            name = resource.name,
            pnd = resource.pnd
    )

    override fun resourceFromModel(model: Person): PersonResource = PersonResource(
            self = null,
            id = model.id,
            description = model.description,
            role = model.role,
            name = model.name,
            pnd = model.pnd
    )
}