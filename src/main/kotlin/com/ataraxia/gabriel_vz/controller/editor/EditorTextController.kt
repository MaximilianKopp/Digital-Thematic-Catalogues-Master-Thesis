package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.model.Text
import com.ataraxia.gabriel_vz.service.TextService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/editor/")
class EditorTextController(
        val textService: TextService
) {

    @GetMapping("/createText")
    fun showTextForm(model: Model): String {
        model.addAttribute("text", Text())
        return "editor/addText"
    }

    @PostMapping("/addText")
    fun addText(@Valid text: Text, bindingResult: BindingResult): String {
        if(bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }
        textService.create(text)
        return "/editor/addText"
    }
}