package com.ataraxia.gabriel_vz.root

import java.time.OffsetDateTime

/**
 * A basic Model implementation.
 *
 * A Model is a business object describing properties and its capabilities.
 *
 * @property id a globally unique identifier.
 * @property title the title of this Model.
 * @property created a UTC [OffsetDateTime] the Model was created.
 * @property modified the most recent UTC [OffsetDateTime] the Model was modified,
 * equals [created] if no modifications did occur.
 * */
abstract class Model(
        open var id: String?,
        open var title: String?,
        open var created: OffsetDateTime?,
        open var modified: OffsetDateTime?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Model) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (created != other.created) return false
        if (modified != other.modified) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (created?.hashCode() ?: 0)
        result = 31 * result + (modified?.hashCode() ?: 0)
        return result
    }
}