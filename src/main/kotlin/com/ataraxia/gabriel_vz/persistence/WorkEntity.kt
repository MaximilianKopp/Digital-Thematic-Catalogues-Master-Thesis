package com.ataraxia.gabriel_vz.persistence

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "work")
class WorkEntity(

        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String? = null,
        var title: String,
        var dateOfCreation: String,
        var dateOfPremiere: String,

        @ManyToOne(fetch = FetchType.LAZY)
        var placeOfPremiere: PlaceEntity?,

        var commentary: String,
        var dedication: String,
        var instrumentation: String,
        var category: String,
        var duration: String,
        var editor: String,

        @ManyToOne(fetch = FetchType.LAZY)
        var relatedText: TextEntity?,

        @ManyToMany(mappedBy = "relatedWorks")
        var discographies: MutableList<DiscographyEntity>? = mutableListOf(),

        @ManyToMany(mappedBy = "relatedWorks")
        var relatedPersons: MutableList<PersonEntity>? = mutableListOf(),

        @OneToMany(
                mappedBy = "relatedWork",
                cascade = [CascadeType.ALL],
                orphanRemoval = true
        )
        var literatureList: MutableList<LiteratureEntity>? = mutableListOf()
) {
    fun addDiscography(discographyEntity: DiscographyEntity) {
        discographies?.add(discographyEntity)
        discographyEntity.relatedWorks?.add(this)
    }

    fun removeDiscography(discographyEntity: DiscographyEntity) {
        discographies?.remove(discographyEntity)
        discographyEntity.relatedWorks?.remove(this)
    }

    fun addPerson(personEntity: PersonEntity) {
        relatedPersons?.add(personEntity)
        personEntity.relatedWorks?.add(this)
    }

    fun removePerson(personEntity: PersonEntity) {
        relatedPersons?.remove(personEntity)
        personEntity.relatedWorks?.remove(this)
    }

    fun addLiterature(literature: LiteratureEntity) {
        literatureList?.add(literature)
        literature.relatedWork = this
    }

    fun removeLiterature(literature: LiteratureEntity) {
        literatureList?.remove(literature)
        literature.relatedWork = null
    }
}