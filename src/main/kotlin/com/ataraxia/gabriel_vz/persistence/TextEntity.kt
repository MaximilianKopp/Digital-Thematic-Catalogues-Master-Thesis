package com.ataraxia.gabriel_vz.persistence

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

        @OneToMany(
                mappedBy = "relatedText",
                cascade = [CascadeType.ALL],
                orphanRemoval = true
        )
        var relatedWorks: MutableList<WorkEntity>? = mutableListOf()
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