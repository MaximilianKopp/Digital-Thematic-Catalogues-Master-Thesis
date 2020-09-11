package com.ataraxia.gabriel_vz.persistence

import com.ataraxia.gabriel_vz.root.AbstractEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Table
@Entity(name = "literature")
class LiteratureEntity(

        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String? = null,
        var author: String,
        var isbn: String,
        var yearOfPublishing: String,

        @JsonBackReference
        @ManyToOne(fetch = FetchType.LAZY)
        var relatedWork: WorkEntity? = null
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is LiteratureEntity) return false

                if (id != other.id) return false
                if (author != other.author) return false
                if (isbn != other.isbn) return false
                if (yearOfPublishing != other.yearOfPublishing) return false

                return true
        }

        override fun hashCode(): Int {
                return 31
        }
}