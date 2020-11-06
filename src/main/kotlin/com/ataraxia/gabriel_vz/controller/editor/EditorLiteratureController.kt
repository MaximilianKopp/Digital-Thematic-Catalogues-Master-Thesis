package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.service.LiteratureService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/editor/")
class EditorLiteratureController(
        val literatureService: LiteratureService
) {

    @GetMapping("/createLiterature")
    fun showLiteratureForm(model: Model): String {
        model.addAttribute("literature", Literature())
        return "editor/addLiterature"
    }

    @PostMapping("/addLiterature")
    fun addLiterature(@Valid literature: Literature, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }
        literatureService.create(literature)
        return "editor/addLiterature"
    }

}