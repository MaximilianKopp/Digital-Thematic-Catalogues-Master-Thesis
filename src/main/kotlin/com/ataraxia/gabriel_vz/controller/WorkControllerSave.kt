//package com.ataraxia.gabriel_vz.controller
//
//import com.ataraxia.gabriel_vz.persistence.WorkEntity
//import com.ataraxia.gabriel_vz.repository.WorkRepository
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/api")
//class WorkControllerSave {
//
//    @Autowired
//    lateinit var workRepository: WorkRepository
//
//    @GetMapping("/works")
//    fun getWorks()
//            : ResponseEntity<List<WorkEntity>> {
//        return try {
//            val works = workRepository.findAll()
//            ResponseEntity(works, HttpStatus.OK)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
//        }
//    }
//
//    @GetMapping("/works/{id}")
//    fun getWork(@PathVariable("id") id: String)
//            : ResponseEntity<WorkEntity> {
//        val work = workRepository.findById(id)
//        return if (work.isPresent)
//            ResponseEntity(work.get(), HttpStatus.OK)
//        else {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }
//
//    @PostMapping("/works")
//    fun createWork(@RequestBody workEntity: WorkEntity)
//            : ResponseEntity<WorkEntity> {
//        return try {
//            ResponseEntity(workRepository.save(workEntity), HttpStatus.ACCEPTED)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
//        }
//    }
//
//    @PutMapping("/works/{id}")
//    fun updateWork(@PathVariable("id") id: String, @RequestBody workEntity: WorkEntity)
//            : ResponseEntity<WorkEntity> {
//        val workData = workRepository.findById(id)
//        return if (workData.isPresent) {
//            val updatedWorkEntity = workData.get()
//                    .apply {
//                        this.title = workEntity.title
//                        this.dateOfCreation = workEntity.dateOfCreation
//                        this.dateOfPremiere = workEntity.dateOfPremiere
//                        this.placeOfPremiere = workEntity.placeOfPremiere
//                        this.incipit = workEntity.incipit
//                        this.commentary = workEntity.commentary
//                        this.dedication = workEntity.dedication
//                        this.instrumentation = workEntity.instrumentation
//                        this.category = workEntity.category
//                        this.duration = workEntity.duration
//                        this.editor = workEntity.editor
//                        this.relatedText = workEntity.relatedText
//                        this.discographies = workEntity.discographies
//                        this.relatedPersons = workEntity.relatedPersons
//                        this.literatureList = workEntity.literatureList
//                    }
//            ResponseEntity(workRepository.save(updatedWorkEntity), HttpStatus.ACCEPTED)
//        } else {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }
//
//    @DeleteMapping("/works")
//    fun deleteWorks()
//            : ResponseEntity<HttpStatus> {
//        return try {
//            workRepository.deleteAll()
//            ResponseEntity(HttpStatus.NO_CONTENT)
//        } catch (e: Exception) {
//            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
//        }
//    }
//
//    @DeleteMapping("/works/{id}")
//    fun deleteWork(@PathVariable("id") id: String)
//            : ResponseEntity<HttpStatus> {
//        return try {
//            workRepository.deleteById(id)
//            ResponseEntity(HttpStatus.NO_CONTENT)
//        } catch (e: Exception) {
//            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
//        }
//    }
//}