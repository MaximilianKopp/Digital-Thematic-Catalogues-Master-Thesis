package com.ataraxia.gabriel_vz.repository

import com.ataraxia.gabriel_vz.persistence.TextEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TextRepository : JpaRepository<TextEntity, String>