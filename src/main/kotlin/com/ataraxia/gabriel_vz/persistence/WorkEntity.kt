package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "work")
class WorkEntity(
        id: String? = null,
        title: String? = null,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var opus: String? = null,
        var dateOfCreation: String? = null,
        var dateOfPremiere: String? = null,

        @JsonManagedReference
        @ManyToOne(
                fetch = FetchType.LAZY,
                cascade = [CascadeType.MERGE]
        )
        var placeOfPremiere: PlaceEntity? = null,

        @OneToOne(
                mappedBy = "relatedWork",
                cascade = [CascadeType.ALL],
                fetch = FetchType.EAGER)
        var incipit: IncipitEntity? = null,
        var commentary: String? = null,
        var dedication: String? = null,
        var instrumentation: String? = null,
        var category: String? = null,
        var duration: String? = null,
        var editor: String? = null,

        @JsonManagedReference
        @ManyToOne(
                fetch = FetchType.LAZY,
                cascade = [
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
                ]
        )
        var relatedText: TextEntity? = null,

        @JsonManagedReference
        @ManyToMany(
                mappedBy = "relatedWorks",
                cascade = [
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
                ]
        )
        var discographies: MutableSet<DiscographyEntity>? = mutableSetOf(),

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
                    CascadeType.MERGE,
                    CascadeType.REMOVE
                ], orphanRemoval = true)
        var literatureList: MutableSet<LiteratureEntity>? = mutableSetOf()
) : com.ataraxia.gabriel_vz.root.Entity(
        id,
        title,
        created,
        modified
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
        literature.relatedWork = this
    }

    fun addIncipit(incipit: IncipitEntity?) {
        this.incipit = incipit
        incipit?.relatedWork = this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WorkEntity) return false
        if (!super.equals(other)) return false

        if (dateOfCreation != other.dateOfCreation) return false
        if (dateOfPremiere != other.dateOfPremiere) return false
        if (placeOfPremiere != other.placeOfPremiere) return false
        if (commentary != other.commentary) return false
        if (dedication != other.dedication) return false
        if (instrumentation != other.instrumentation) return false
        if (category != other.category) return false
        if (duration != other.duration) return false
        if (editor != other.editor) return false
        if (relatedText != other.relatedText) return false
        if (discographies != other.discographies) return false
        if (relatedPersons != other.relatedPersons) return false
        if (literatureList != other.literatureList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + dateOfCreation.hashCode()
        result = 31 * result + dateOfPremiere.hashCode()
        result = 31 * result + commentary.hashCode()
        result = 31 * result + dedication.hashCode()
        result = 31 * result + instrumentation.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + editor.hashCode()
        result = 31 * result + discographies.hashCode()
        return result
    }

}