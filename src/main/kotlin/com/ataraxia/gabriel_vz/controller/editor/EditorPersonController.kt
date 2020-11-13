package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.model.Person
import com.ataraxia.gabriel_vz.service.PersonService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/editor/")
class EditorPersonController(
        val personService: PersonService
) : com.ataraxia.gabriel_vz.root.Controller<Model, Person>() {

    @GetMapping("/createPerson")
    override fun showAddForm(m: Model): String {
        m.addAttribute("person", Person())
        return "/editor/addPerson"
    }

    @PostMapping("/addPerson")
    override fun add(@Valid type: Person, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.hasErrors())
        }
        personService.create(type)
        return "redirect:/persons"
    }

    @GetMapping("/editPerson")
    override fun showUpdateForm(id: String, m: Model): String {
        val person = personService.get(id).toOption().orNull()
        m.addAttribute("person", person)
        return "/editor/editPerson"
    }

    @PostMapping("/updatePerson/{id}")
    override fun update(@PathVariable("id") id: String, @Valid type: Person): String {
        personService.update(id, type)
        return "redirect:/persons"
    }

    @GetMapping("/deletePerson")
    override fun deleteById(@RequestParam("id") id: String): String {
        personService.delete(id)
        return "redirect:/persons"
    }

    @GetMapping("/deletePersons")
    override fun deleteAll(): String {
        personService.deleteAll()
        return "redirect:/persons"
    }
}