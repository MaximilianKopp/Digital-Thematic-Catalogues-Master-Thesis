package com.ataraxia.gabriel_vz.repository

import com.ataraxia.gabriel_vz.persistence.IncipitEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IncipitRepository : JpaRepository<IncipitEntity, String>