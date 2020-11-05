package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.factory.PlaceFactory
import com.ataraxia.gabriel_vz.model.Work
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
        val placeFactory: PlaceFactory,
        val placeService: PlaceService,
        val textService: TextService
) {

    @GetMapping("/createWork")
    fun createWork(model: Model): String {
        model.addAttribute("work", Work())
        model.addAttribute("places", placeService.getAll().toOption().orNull())
        model.addAttribute("texts", textService.getAll().toOption().orNull())
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
        }

        workService.create(work!!)
        return "editor/addWork"
    }
}