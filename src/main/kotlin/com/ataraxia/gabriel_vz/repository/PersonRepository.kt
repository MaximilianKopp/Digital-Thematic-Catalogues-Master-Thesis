package com.ataraxia.gabriel_vz.repository

import com.ataraxia.gabriel_vz.persistence.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<PersonEntity, String>