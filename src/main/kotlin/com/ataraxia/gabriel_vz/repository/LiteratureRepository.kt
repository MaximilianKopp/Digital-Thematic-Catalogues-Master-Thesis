package com.ataraxia.gabriel_vz.repository

import com.ataraxia.gabriel_vz.persistence.LiteratureEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LiteratureRepository : JpaRepository<LiteratureEntity, String>