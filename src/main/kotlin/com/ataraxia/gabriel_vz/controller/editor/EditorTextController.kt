package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.model.Text
import com.ataraxia.gabriel_vz.service.TextService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
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
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }
        textService.create(text)
        return "redirect:/editor/createText"
    }

    @GetMapping("/editText")
    fun updateTextForm(@RequestParam("id") id: String, model: Model): String {
        val text = textService.get(id).toOption().orNull()
        model.addAttribute("text", text)
        return "/editor/editText"
    }

    @PostMapping("/updateText")
    fun editText(@RequestParam("id") id: String, @Valid text: Text): String {
        textService.update(id, text)
        return "/editor/editText"
    }

    @GetMapping("/deleteText")
    fun deleteText(@RequestParam("id") id: String): String {
        textService.delete(id)
        return "/common_user/texts"
    }
}