package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.service.LiteratureService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/editor/")
class EditorLiteratureController(
        val literatureService: LiteratureService
) : com.ataraxia.gabriel_vz.root.Controller<Model, Literature>() {

    @GetMapping("/createLiterature")
    override fun showAddForm(m: Model): String {
        m.addAttribute("literature", Literature())
        return "editor/addLiterature"
    }

    @PostMapping("/addLiterature")
    override fun add(@Valid type: Literature, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }
        literatureService.create(type)
        return "editor/addLiterature"
    }

    @GetMapping("/editLiterature")
    override fun showUpdateForm(@RequestParam("id") id: String, m: Model): String {
        val literature = literatureService.get(id).toOption().orNull()
        m.addAttribute("literature", literature)
        return "/editor/editLiterature"
    }

    @PostMapping("/updateLiterature/{id}")
    override fun update(@PathVariable("id") id: String, @Valid type: Literature): String {
        literatureService.update(id, type)
        return "redirect:/literature"
    }

    @GetMapping("deleteReference")
    override fun deleteById(@RequestParam("id") id: String): String {
        literatureService.delete(id)
        return "redirect:/literature"
    }

    @GetMapping("deleteLiterature")
    override fun deleteAll(): String {
        literatureService.deleteAll()
        return "redirect:/literature"
    }
}