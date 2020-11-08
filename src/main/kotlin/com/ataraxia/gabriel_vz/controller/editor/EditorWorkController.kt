package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.factory.*
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.service.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@RequestMapping("/editor/")
@Controller
class EditorWorkController(
        val workService: WorkService,
        val workFactory: WorkFactory,
        val placeFactory: PlaceFactory,
        val placeService: PlaceService,
        val textService: TextService,
        val literatureFactory: LiteratureFactory,
        val literatureService: LiteratureService,
        val discographyService: DiscographyService,
        val discographyFactory: DiscographyFactory,
        val personService: PersonService,
        val personFactory: PersonFactory
) {

    @GetMapping("/createWork")
    fun createWork(model: Model): String {
        model.addAttribute("work", Work())
        model.addAttribute("places", placeService.getAll().toOption().orNull())
        model.addAttribute("texts", textService.getAll().toOption().orNull())
        model.addAttribute("literature", literatureService.getAll().toOption().orNull())
        model.addAttribute("discography", discographyService.getAll().toOption().orNull())
        model.addAttribute("persons", personService.getAll().toOption().orNull())
        return "editor/addWork"
    }

    @PostMapping(value = ["/addWork"])
    fun addWork(@Valid work: Work?, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }

        work?.apply {
            if (placeOfPremiere?.id == "") {
                this.placeOfPremiere = null
            }
            if (relatedText?.id == "") {
                this.relatedText = null
            }
            if (relatedText?.id == "") {
                this.relatedText = null
            }
            if (incipit?.clef == "") {
                this.incipit = null
            }
        }

        work?.literatureList?.removeIf { it.id == null }
        work?.literatureList?.map { literatureFactory.entityFromModel(it).relatedWorks?.add(workFactory.entityFromModel(work)) }

        work?.discographies?.removeIf { it.id == null }
        work?.discographies?.map { discographyFactory.entityFromModel(it).relatedWorks?.add(workFactory.entityFromModel(work)) }

        work?.relatedPersons?.removeIf { it.id == null }
        work?.relatedPersons?.map { personFactory.entityFromModel(it).relatedWorks?.add(workFactory.entityFromModel(work)) }

        workService.create(work!!)
        return "editor/addWork"
    }
}