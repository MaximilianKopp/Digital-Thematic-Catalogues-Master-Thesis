package com.ataraxia.gabriel_vz.persistence

import arrow.core.Option
import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "incipit")
class IncipitEntity(
        id: String? = null,
        title: String? = null,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var text: String? = null,
        var clef: String? = null,
        var keysig: String? = null,
        var timesig: String? = null,
        var score: String? = null,
        var description: String? = null,

        @JsonBackReference
        @OneToOne(mappedBy = "incipit", orphanRemoval = true )
        var relatedWork: WorkEntity? = null
) : com.ataraxia.gabriel_vz.root.Entity(
        id,
        title,
        created,
        modified
) {

    fun addWork(workEntity: WorkEntity) {
        relatedWork = workEntity
        workEntity.addIncipit(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IncipitEntity) return false
        if (!super.equals(other)) return false

        if (text != other.text) return false
        if (keysig != other.keysig) return false
        if (timesig != other.timesig) return false
        if (score != other.score) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + keysig.hashCode()
        result = 31 * result + timesig.hashCode()
        result = 31 * result + score.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }

}