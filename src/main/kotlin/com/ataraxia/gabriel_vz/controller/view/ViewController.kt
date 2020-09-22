package com.ataraxia.gabriel_vz.controller.view

import arrow.core.right
import com.ataraxia.gabriel_vz.service.*
import org.springframework.expression.spel.SpelEvaluationException
import org.springframework.expression.spel.SpelMessage
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.thymeleaf.exceptions.TemplateProcessingException

@Controller
@RequestMapping("/")
class ViewController(
        private val workService: WorkService,
        private val incipitService: IncipitService,
        private val literatureService: LiteratureService,
        private val textService: TextService,
        private val placeService: PlaceService,
        private val discographyService: DiscographyService,
        private val personService: PersonService
) {

    @GetMapping
    fun index(): String {
        return "common_user/index"
    }

    @GetMapping("/statistics")
    fun statistics(): String {
        return "/common_user/statistics"
    }

//    @GetMapping("/works")
//    fun index(model: Model): String {
//        return findPaginated(1, "title", "asc", model)
//    }
//
//    @GetMapping("works/page/{pageNo}")
//    fun findPaginated(@PathVariable("pageNo") pageNo: Int,
//                      @RequestParam("sortField") sortfield: String,
//                      @RequestParam("sortDir") sortDir: String, model: Model): String {
//        val pageSize = 8
//
//        val page = workService.findPaginated(pageNo, pageSize, sortfield, sortDir)
//        val works = page.content
//        model.addAttribute("currentPage", pageNo)
//        model.addAttribute("totalPages", page.totalPages)
//        model.addAttribute("totalItems", page.totalElements)
//        model.addAttribute("sortField", sortfield)
//        model.addAttribute("sortDir", sortDir)
//        model.addAttribute("reverseSortDir",
//                when {
//                    sortDir.equals("asc") -> "desc"
//                    else -> "asc"
//                })
//
//        model.addAttribute("works", works)
//        return "works"
//    }

    @GetMapping("/works")
    fun allWorks(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") pageNumber: Int,
                 @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
                 model: Model): String? {
        model.addAttribute("works", workService.getPage(pageNumber, size))
        return "common_user/works"
    }

    @GetMapping("/work")
    fun oneWork(@RequestParam("id") id: String, model: Model): String? {
        val work = workService.get(id)
        model.addAttribute("work", work
                .fold(
                        ifLeft = { throw  SpelEvaluationException(SpelMessage.PROPERTY_OR_FIELD_NOT_READABLE_ON_NULL) },
                        ifRight = { it }
                )
        )
        return "common_user/workdetails"
    }

    @GetMapping("/incipits")
    fun allIncipits(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") pageNumber: Int,
                    @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
                    model: Model): String? {
        model.addAttribute("incipits", incipitService.getPage(pageNumber, size))
        return "common_user/incipits"
    }

    @GetMapping("/incipit")
    fun oneIncipit(@RequestParam("id") id: String, model: Model): String? {
        val incipit = incipitService.get(id)
        model.addAttribute("incipit", incipit
                .fold(
                        ifLeft = { throw  SpelEvaluationException(SpelMessage.PROPERTY_OR_FIELD_NOT_READABLE_ON_NULL) },
                        ifRight = { it }
                )
        )
        return "common_user/incipitdetails"
    }

    @GetMapping("/literature")
    fun allLiterature(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") pageNumber: Int,
                      @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
                      model: Model): String? {
        model.addAttribute("literature", literatureService.getPage(pageNumber, size))
        return "common_user/literature"
    }

    @GetMapping("/reference")
    fun oneReference(@RequestParam("id") id: String, model: Model): String? {
        val reference = literatureService.get(id)
        model.addAttribute("reference", reference
                .fold(
                        ifLeft = { throw  SpelEvaluationException(SpelMessage.PROPERTY_OR_FIELD_NOT_READABLE_ON_NULL) },
                        ifRight = { it }
                )
        )
        return "common_user/literaturedetails"
    }

    @GetMapping("/texts")
    fun allTexts(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") pageNumber: Int,
                 @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
                 model: Model): String? {
        model.addAttribute("texts", textService.getPage(pageNumber, size))
        return "common_user/texts"
    }

    @GetMapping("/text")
    fun oneText(@RequestParam("id") id: String, model: Model): String? {
        val text = textService.get(id)
        model.addAttribute("text", text
                .fold(
                        ifLeft = { throw  SpelEvaluationException(SpelMessage.PROPERTY_OR_FIELD_NOT_READABLE_ON_NULL) },
                        ifRight = { it }
                )
        )
        return "common_user/textdetails"
    }

    @GetMapping("/places")
    fun allPlaces(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") pageNumber: Int,
                  @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
                  model: Model): String? {
        model.addAttribute("places", placeService.getPage(pageNumber, size))
        return "common_user/places"
    }

    @GetMapping("/place")
    fun onePlace(@RequestParam("id") id: String, model: Model): String? {
        val place = placeService.get(id)
        model.addAttribute("place", place
                .fold(
                        ifLeft = { throw  SpelEvaluationException(SpelMessage.PROPERTY_OR_FIELD_NOT_READABLE_ON_NULL) },
                        ifRight = { it }
                )
        )
        return "common_user/placedetails"
    }

    @GetMapping("/discography")
    fun allDiscographies(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") pageNumber: Int,
                         @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
                         model: Model): String? {
        model.addAttribute("discographies", discographyService.getPage(pageNumber, size))
        return "common_user/discography"
    }

    @GetMapping("/record")
    fun oneDiscography(@RequestParam("id") id: String, model: Model): String? {
        val discography = discographyService.get(id)
        model.addAttribute("discography", discography
                .fold(
                        ifLeft = { throw  SpelEvaluationException(SpelMessage.PROPERTY_OR_FIELD_NOT_READABLE_ON_NULL) },
                        ifRight = { it }
                )
        )
        return "common_user/discographydetails"
    }

    @GetMapping("/persons")
    fun allPersons(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") pageNumber: Int,
                   @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
                   model: Model): String? {
        model.addAttribute("persons", personService.getPage(pageNumber, size))
        return "common_user/persons"
    }

    @GetMapping("/person")
    fun onePerson(@RequestParam("id") id: String, model: Model): String? {
        val persons = personService.get(id)
        model.addAttribute("persons", persons
                .fold(
                        ifLeft = { throw  SpelEvaluationException(SpelMessage.PROPERTY_OR_FIELD_NOT_READABLE_ON_NULL) },
                        ifRight = { it }
                )
        )
        return "common_user/persondetails"
    }


}