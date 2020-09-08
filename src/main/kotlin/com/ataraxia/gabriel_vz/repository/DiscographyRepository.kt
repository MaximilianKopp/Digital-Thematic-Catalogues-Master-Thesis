package com.ataraxia.gabriel_vz.repository

import com.ataraxia.gabriel_vz.persistence.DiscographyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DiscographyRepository : JpaRepository<DiscographyEntity, String>