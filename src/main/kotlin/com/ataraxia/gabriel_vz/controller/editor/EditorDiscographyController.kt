package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.model.Discography
import com.ataraxia.gabriel_vz.service.DiscographyService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/editor/")
class EditorDiscographyController(
        val discographyService: DiscographyService
) : com.ataraxia.gabriel_vz.root.Controller<Model, Discography>() {

    @GetMapping("/createDiscography")
    override fun showAddForm(m: Model): String {
        m.addAttribute("discography", Discography())
        return "/editor/addDiscography"
    }

    @PostMapping("/addDiscography")
    override fun add(@Valid type: Discography, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }
        discographyService.create(type)
        return "redirect:/discography"
    }

    @GetMapping("/editRecord")
    override fun showUpdateForm(@RequestParam("id") id: String, m: Model): String {
        val discography = discographyService.get(id).toOption().orNull()
        m.addAttribute("discography", discography)
        return "editor/editDiscography"
    }

    @PostMapping("/updateRecord/{id}")
    override fun update(@PathVariable("id") id: String, @Valid type: Discography): String {
        discographyService.update(id, type)
        return "redirect:/discography"
    }

    @GetMapping("/deleteRecord")
    override fun deleteById(@RequestParam("id") id: String): String {
        discographyService.delete(id)
        return "redirect:/discography"
    }

    @GetMapping("/deleteDiscography")
    override fun deleteAll(): String {
        discographyService.deleteAll()
        return "redirect:/discography"
    }
}