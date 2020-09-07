package com.ataraxia.gabriel_vz.model

import com.ataraxia.gabriel_vz.root.AbstractEntity

class Incipit(
        override var id: String?,
        var text: String,
        var keysig: String,
        var timesig: String,
        var score: String,
        var description: String
) : AbstractEntity(id) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Incipit) return false
        if (!super.equals(other)) return false

        if (text != other.text) return false
        if (keysig != other.keysig) return false
        if (timesig != other.timesig) return false
        if (score != other.score) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + keysig.hashCode()
        result = 31 * result + timesig.hashCode()
        result = 31 * result + score.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}