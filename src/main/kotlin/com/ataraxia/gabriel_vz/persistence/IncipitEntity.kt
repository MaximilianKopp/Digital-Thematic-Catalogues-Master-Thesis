package com.ataraxia.gabriel_vz.persistence

import com.ataraxia.gabriel_vz.root.AbstractEntity
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

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
        var description: String
)