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
)