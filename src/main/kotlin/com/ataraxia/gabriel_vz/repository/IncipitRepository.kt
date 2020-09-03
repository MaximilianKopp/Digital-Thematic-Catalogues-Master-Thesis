package com.ataraxia.gabriel_vz.repository

import com.ataraxia.gabriel_vz.persistence.IncipitEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IncipitRepository : JpaRepository<IncipitEntity, String>