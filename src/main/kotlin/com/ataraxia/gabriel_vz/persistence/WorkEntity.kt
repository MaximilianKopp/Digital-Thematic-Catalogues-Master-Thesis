package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "work")
class WorkEntity(

        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String? = "",
        var title: String,
        var dateOfCreation: String,
        var dateOfPremiere: String,

        @JsonManagedReference
        @ManyToOne(
                fetch = FetchType.LAZY,
                cascade = [CascadeType.ALL]
        )
        var placeOfPremiere: PlaceEntity? = null,

        @OneToOne(
                mappedBy = "relatedWork",
                cascade = [CascadeType.ALL],
                fetch = FetchType.EAGER)
        var incipit: IncipitEntity? = null,
        var commentary: String,
        var dedication: String,
        var instrumentation: String,
        var category: String,
        var duration: String,
        var editor: String,

        @JsonManagedReference
        @ManyToOne(
                fetch = FetchType.LAZY,
                cascade = [CascadeType.ALL]
        )
        var relatedText: TextEntity?,

        @JsonManagedReference
        @ManyToMany(mappedBy = "relatedWorks",
                cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY)
        var discographies: MutableSet<DiscographyEntity> = mutableSetOf(),

        @JsonManagedReference
        @ManyToMany(mappedBy = "relatedWorks",
                cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY)
        var relatedPersons: MutableSet<PersonEntity>? = mutableSetOf(),

        @JsonManagedReference
        @OneToMany(
                mappedBy = "relatedWork",
                cascade = [CascadeType.ALL]
        )
        var literatureList: MutableSet<LiteratureEntity>? = mutableSetOf()
) {
    fun addDiscography(discographyEntity: DiscographyEntity) {
        discographies.add(discographyEntity)
        discographyEntity.relatedWorks?.add(this)
    }

    fun removeDiscography(discographyEntity: DiscographyEntity) {
        discographies.remove(discographyEntity)
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
        literature.relatedWork = this
    }

    fun addIncipit(incipit: IncipitEntity?) {
        this.incipit = incipit
        incipit?.relatedWork = this
    }

}