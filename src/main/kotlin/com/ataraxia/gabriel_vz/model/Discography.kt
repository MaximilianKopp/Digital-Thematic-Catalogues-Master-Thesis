package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.Model
import java.time.OffsetDateTime

class Discography(
        id: String? = null,
        title: String? = null,
        created: OffsetDateTime = OffsetDateTime.now(),
        modified: OffsetDateTime = OffsetDateTime.now(),
        var label: String? = null,
        var recordId: String? = null,
        var dateOfPublishing: String? = null,
        var musicians: MutableSet<Person>? = mutableSetOf(),
        var relatedWorks: Map<String?, String?> = mutableMapOf()
) : Model(
        id,
        title,
        created,
        modified
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Discography) return false
        if (!super.equals(other)) return false

        if (label != other.label) return false
        if (recordId != other.recordId) return false
        if (dateOfPublishing != other.dateOfPublishing) return false
        if (musicians != other.musicians) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + (recordId?.hashCode() ?: 0)
        result = 31 * result + (dateOfPublishing?.hashCode() ?: 0)
        result = 31 * result + (musicians?.hashCode() ?: 0)
        return result
    }
}