package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "text")
class TextEntity(

        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String? = null,
        var title: String,
        var author: String,
        var excerpt: String,

        @JsonBackReference
        @OneToMany(
                mappedBy = "relatedText",
                orphanRemoval = true
        )
        var relatedWorks: MutableSet<WorkEntity>? = mutableSetOf()
) {
    fun addWork(workEntity: WorkEntity) {
        relatedWorks?.add(workEntity)
        workEntity.relatedText = this
    }

    fun removeWork(workEntity: WorkEntity) {
        relatedWorks?.remove(workEntity)
        workEntity.relatedText = null
    }
}