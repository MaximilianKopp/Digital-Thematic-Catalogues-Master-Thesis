package com.ataraxia.gabriel_vz.repository

import com.ataraxia.gabriel_vz.persistence.WorkEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkRepository : JpaRepository<WorkEntity, String>