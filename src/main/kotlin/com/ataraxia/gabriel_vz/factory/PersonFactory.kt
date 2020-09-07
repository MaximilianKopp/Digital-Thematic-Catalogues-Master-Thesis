package com.ataraxia.gabriel_vz.factory

import com.ataraxia.gabriel_vz.model.Person
import com.ataraxia.gabriel_vz.persistence.DiscographyEntity
import com.ataraxia.gabriel_vz.persistence.PersonEntity
import com.ataraxia.gabriel_vz.resource.PersonResource
import com.ataraxia.gabriel_vz.root.Factory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PersonFactory : Factory<Person, PersonEntity, PersonResource>() {

    var discographyFactory: DiscographyFactory = DiscographyFactory(this)

    var workFactory: WorkFactory = WorkFactory(discographyFactory, LiteratureFactory(), this)

    override fun modelFromEntity(entity: PersonEntity): Person = Person(
            id = entity.id,
            description = entity.description,
            role = entity.role,
            name = entity.name,
            pnd = entity.pnd,
            relatedDiscographies = entity.relatedDiscographies?.map(
                    discographyFactory::modelFromEntity
            )!!.toMutableSet(),
            relatedWorks = entity.relatedWorks?.map(
                    workFactory::modelFromEntity
            )!!.toMutableSet()
    )

    override fun entityFromModel(model: Person): PersonEntity = PersonEntity(
            id = model.id,
            description = model.description,
            role = model.role,
            name = model.name,
            pnd = model.pnd,
            relatedDiscographies = model.relatedDiscographies.map(
                    discographyFactory::entityFromModel
            ).toMutableSet(),
            relatedWorks = model.relatedWorks.map(
                    workFactory::entityFromModel
            ).toMutableSet()
    )

    override fun modelFromResource(resource: PersonResource): Person = Person(
            id = resource.id,
            description = resource.description,
            role = resource.role,
            name = resource.name,
            pnd = resource.pnd,
            relatedDiscographies = resource.relatedDiscographies.map(
                    discographyFactory::modelFromResource
            ).toMutableSet(),
            relatedWorks = resource.relatedWorks.map(
                    workFactory::modelFromResource
            ).toMutableSet()
    )

    override fun resourceFromModel(model: Person): PersonResource = PersonResource(
            self = null,
            id = model.id,
            description = model.description,
            role = model.role,
            name = model.name,
            pnd = model.pnd,
            relatedDiscographies = model.relatedDiscographies.map(
                    discographyFactory::resourceFromModel
            ).toMutableSet(),
            relatedWorks = model.relatedWorks.map(
                    workFactory::resourceFromModel
            ).toMutableSet()
    )
}