package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.factory.IncipitFactory
import com.ataraxia.gabriel_vz.model.Incipit
import com.ataraxia.gabriel_vz.service.IncipitService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@Controller
@RequestMapping("/editor/")
class EditorIncipitController(
        val incipitService: IncipitService
) {

    @GetMapping("/editIncipit")
    fun showUpdateForm(@RequestParam("id") id: String, m: Model): String {
        val incipit = incipitService.get(id).toOption().orNull()
        m.addAttribute("incipit", incipit)
        return "editor/editIncipit"
    }

    @PostMapping("/updateIncipit/{id}")
    fun update(@PathVariable("id") id: String, @Valid type: Incipit): String {
        incipitService.update(id, type)
        return "redirect:/incipits"
    }
}