package com.ataraxia.gabriel_vz.service

import com.ataraxia.gabriel_vz.controller.paging.Paged
import com.ataraxia.gabriel_vz.controller.paging.Paging
import com.ataraxia.gabriel_vz.factory.WorkFactory
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.repository.WorkRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class SearchService(
        val workRepository: WorkRepository,
        val workFactory: WorkFactory
) {

    lateinit var workPage: Page<Work>

    fun pagedResults(work: Work, pageNumber: Int, size: Int): Paged<Work> {
        val pageable: Pageable = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC, "id")

        when {
            work.title?.isNotEmpty()!! -> workPage = workRepository.findByTitle(work.title!!, pageable).map { workFactory.modelFromEntity(it) }
            work.category?.isNotEmpty()!! -> workPage = workRepository.findByCategory(work.category!!, pageable).map { workFactory.modelFromEntity(it) }
            work.instrumentation?.isNotEmpty()!! -> workPage = workRepository.findByInstrumentation(work.instrumentation!!, pageable).map { workFactory.modelFromEntity(it) }
            work.duration?.isNotEmpty()!! -> workPage = workRepository.findByDuration(work.duration!!, pageable).map { workFactory.modelFromEntity(it) }
        }
        return Paged(workPage, Paging.of(workPage.totalPages, pageNumber, size))
    }
}