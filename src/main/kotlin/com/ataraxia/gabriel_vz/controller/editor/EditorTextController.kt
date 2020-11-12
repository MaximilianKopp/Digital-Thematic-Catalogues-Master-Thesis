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
) : com.ataraxia.gabriel_vz.root.Controller<Model, Text>() {


    @GetMapping("/createText")
    override fun showAddForm(m: Model): String {
        m.addAttribute("text", Text())
        return "editor/addText"
    }

    @PostMapping("/addText")
    override fun add(@Valid type: Text, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }
        textService.create(type)
        return "redirect:/texts"
    }

    @GetMapping("/editText")
    override fun showUpdateForm(@RequestParam("id") id: String, m: Model): String {
        val text = textService.get(id).toOption().orNull()
        m.addAttribute("text", text)
        return "/editor/editText"
    }

    @PostMapping("/updateText/{id}")
    override fun update(@PathVariable("id") id: String, @Valid type: Text): String {
        textService.update(id, type)
        return "redirect:/texts"
    }

    @GetMapping("/deleteText")
    override fun deleteById(@RequestParam("id") id: String): String {
        textService.delete(id)
        return "redirect:/texts"
    }

    @GetMapping("/deleteTexts")
    override fun deleteAll(): String {
        textService.deleteAll()
        return "redirect:/texts"
    }
}