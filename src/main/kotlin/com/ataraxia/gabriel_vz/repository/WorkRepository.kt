package com.ataraxia.gabriel_vz.repository

import com.ataraxia.gabriel_vz.persistence.WorkEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface WorkRepository : JpaRepository<WorkEntity, String> {

    @Query("SELECT w FROM WorkEntity w WHERE w.title=:title")
    fun findByTitle(@Param("title") title: String, pageable: Pageable): Page<WorkEntity>

    @Query("SELECT w FROM WorkEntity w WHERE w.category=:category")
    fun findByCategory(@Param("category") category: String, pageable: Pageable): Page<WorkEntity>

    @Query("SELECT w FROM WorkEntity w WHERE w.instrumentation=:instrumentation")
    fun findByInstrumentation(@Param("instrumentation") instrumentation: String, pageable: Pageable): Page<WorkEntity>

    @Query("SELECT w FROM WorkEntity w WHERE w.duration=:duration")
    fun findByDuration(@Param("duration") duration: String, pageable: Pageable): Page<WorkEntity>

}