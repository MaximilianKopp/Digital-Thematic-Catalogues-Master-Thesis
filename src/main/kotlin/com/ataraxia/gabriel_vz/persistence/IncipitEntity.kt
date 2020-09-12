package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "incipit")
class IncipitEntity(

        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String? = null,
        var text: String,
        var keysig: String,
        var timesig: String,
        var score: String,
        var description: String,

        @JsonBackReference
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "work", referencedColumnName = "id")
        var relatedWork: WorkEntity? = null
) {

        fun addWork(workEntity: WorkEntity) {
                relatedWork = workEntity
                workEntity.addIncipit(this)
        }

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is IncipitEntity) return false

                if (id != other.id) return false
                if (text != other.text) return false
                if (keysig != other.keysig) return false
                if (timesig != other.timesig) return false
                if (score != other.score) return false
                if (description != other.description) return false

                return true
        }

        override fun hashCode(): Int {
                return 31
        }


}