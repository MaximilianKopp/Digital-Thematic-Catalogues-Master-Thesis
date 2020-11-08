package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.factory.LiteratureFactory
import com.ataraxia.gabriel_vz.factory.PlaceFactory
import com.ataraxia.gabriel_vz.factory.WorkFactory
import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.persistence.LiteratureEntity
import com.ataraxia.gabriel_vz.service.LiteratureService
import com.ataraxia.gabriel_vz.service.PlaceService
import com.ataraxia.gabriel_vz.service.TextService
import com.ataraxia.gabriel_vz.service.WorkService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/editor/")
@Controller
class EditorWorkController(
        val workService: WorkService,
        val workFactory: WorkFactory,
        val literatureFactory: LiteratureFactory,
        val placeFactory: PlaceFactory,
        val placeService: PlaceService,
        val textService: TextService,
        val literatureService: LiteratureService
) {

    @GetMapping("/createWork")
    fun createWork(work: Work, model: Model): String {
        model.addAttribute("work", work)
        model.addAttribute("places", placeService.getAll().toOption().orNull())
        model.addAttribute("texts", textService.getAll().toOption().orNull())
        model.addAttribute("literature", literatureService.getAll().toOption().orNull())
        return "editor/addWork"
    }

    @PostMapping(value = ["/addWork"])
    fun addWork(@Validated work: Work?, bindingResult: BindingResult): String {
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
            if(incipit?.clef == "") {
                this.incipit = null
            }
        }

        work?.literatureList?.removeIf { it.id == null }
        work?.literatureList?.map { literatureFactory.entityFromModel(it).relatedWorks?.add(workFactory.entityFromModel(work)) }
        println("Größe " + work?.literatureList?.size)

        workService.create(work!!)
        return "editor/addWork"
    }
}