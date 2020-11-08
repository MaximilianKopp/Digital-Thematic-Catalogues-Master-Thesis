package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.model.Discography
import com.ataraxia.gabriel_vz.service.DiscographyService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/editor/")
class EditorDiscographyController(
        val discographyService: DiscographyService
) {

    @GetMapping("/createDiscography")
    fun createDiscography(model: Model): String {
        model.addAttribute("discography", Discography())
        return "/editor/addDiscography"
    }

    @PostMapping("/addDiscography")
    fun addDiscography(@Valid discography: Discography, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }
        discographyService.create(discography)
        return "/editor/addDiscography"
    }
}