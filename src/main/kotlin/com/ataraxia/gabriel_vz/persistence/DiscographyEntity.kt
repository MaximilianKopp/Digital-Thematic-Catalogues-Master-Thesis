package com.ataraxia.gabriel_vz.persistence

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "discography")
class DiscographyEntity(

        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String? = null,
        var title: String,
        var label: String,
        var recordId: String,
        var dateOfPublishing: String,

        @ManyToMany(mappedBy = "relatedDiscographies")
        var musicians: MutableList<PersonEntity>?,

        @ManyToMany(cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE])
        var relatedWorks: MutableList<WorkEntity>? = mutableListOf()
)