package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "incipit")
class IncipitEntity(
        id: String?,
        title: String?,
        created: OffsetDateTime?,
        modified: OffsetDateTime?,
        var text: String,
        var keysig: String,
        var timesig: String,
        var score: String,
        var description: String,

        @JsonBackReference
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "work", referencedColumnName = "id")
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