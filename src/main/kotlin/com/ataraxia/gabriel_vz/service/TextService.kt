package com.ataraxia.gabriel_vz.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ataraxia.gabriel_vz.controller.paging.Paged
import com.ataraxia.gabriel_vz.controller.paging.Paging
import com.ataraxia.gabriel_vz.factory.TextFactory
import com.ataraxia.gabriel_vz.model.Text
import com.ataraxia.gabriel_vz.repository.TextRepository
import com.ataraxia.gabriel_vz.root.Service
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@org.springframework.stereotype.Service
class TextService(
        private val textFactory: TextFactory,
        private val textRepository: TextRepository
) : Service<Text>() {

    override fun getAll(): Either<Exception, MutableList<Text>> = try {
        textRepository.findAll()
                .map(textFactory::modelFromEntity)
                .toMutableList()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun get(id: String): Either<Exception, Text> = try {
        textRepository.findById(id)
                .map(textFactory::modelFromEntity)
                .get()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun create(m: Text): Either<Exception, Text> = try {
        val textEntity = textRepository.save(textFactory.entityFromModel(m))
        textFactory.modelFromEntity(textEntity).right()
    } catch (e: Exception) {
        e.left()
    }

    override fun update(id: String, m: Text): Either<Exception, Text> = try {
        val textData = textRepository.findById(id)
        val textEntity = textFactory.entityFromModel(m)
        val updatedTextEntity = textData.get()
                .apply {
                    this.title = textEntity.title
                    this.author = textEntity.author
                    this.excerpt = textEntity.excerpt
                    this.relatedWorks = textEntity.relatedWorks
                }
        textRepository.save(updatedTextEntity)
        textFactory
                .modelFromEntity(updatedTextEntity)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun deleteAll(): Either<Exception, Unit> = try {
        textRepository
                .deleteAll()
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun delete(id: String): Either<Exception, Unit> = try {
        textRepository
                .deleteById(id)
                .right()
    } catch (e: Exception) {
        e.left()
    }

    override fun getPage(pageNumber: Int, size: Int): Paged<Text> {
        val request: PageRequest = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC, "id")
        val textPage = textRepository.findAll(request)
                .map(textFactory::modelFromEntity)
        return Paged(textPage, Paging.of(textPage.totalPages, pageNumber, size))
    }
}