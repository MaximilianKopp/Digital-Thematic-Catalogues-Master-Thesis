package com.ataraxia.gabriel_vz.errorhandling

data class ResourceNotFoundException(override val message: String) : Exception(message)