package com.ataraxia.gabriel_vz.repository

import com.ataraxia.gabriel_vz.persistence.PlaceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceRepository : JpaRepository<PlaceEntity, String>