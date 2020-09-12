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
                cascade = [
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
                ]
        )
        var relatedText: TextEntity?,

        @JsonManagedReference
        @ManyToMany(
                mappedBy = "relatedWorks",
                cascade = [
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
                ]
        )
        var discographies: MutableSet<DiscographyEntity> = mutableSetOf(),

        @JsonManagedReference
        @ManyToMany(
                mappedBy = "relatedWorks",
                cascade = [
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
                ]
        )
        var relatedPersons: MutableSet<PersonEntity>? = mutableSetOf(),

        @JsonManagedReference
        @OneToMany(mappedBy = "relatedWork",
                cascade = [
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
                ], orphanRemoval = true)
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WorkEntity) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (dateOfCreation != other.dateOfCreation) return false
        if (dateOfPremiere != other.dateOfPremiere) return false
        if (placeOfPremiere != other.placeOfPremiere) return false
        if (commentary != other.commentary) return false
        if (dedication != other.dedication) return false
        if (instrumentation != other.instrumentation) return false
        if (category != other.category) return false
        if (duration != other.duration) return false
        if (editor != other.editor) return false

        return true
    }

    override fun hashCode(): Int {
        return 31
    }
}