package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Discography(
        override var id: String?,
        var title: String,
        var label: String,
        var recordId: String,
        var dateOfPublishing: String,
        var musicians: MutableSet<Person>?
) : AbstractEntity(id) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Discography) return false
        if (!super.equals(other)) return false

        if (title != other.title) return false
        if (label != other.label) return false
        if (recordId != other.recordId) return false
        if (dateOfPublishing != other.dateOfPublishing) return false
        if (musicians != other.musicians) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + recordId.hashCode()
        result = 31 * result + dateOfPublishing.hashCode()
        result = 31 * result + musicians.hashCode()
        return result
    }
}