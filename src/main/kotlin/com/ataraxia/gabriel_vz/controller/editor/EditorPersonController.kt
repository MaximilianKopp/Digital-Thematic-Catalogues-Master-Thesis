package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.model.Person
import com.ataraxia.gabriel_vz.service.PersonService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/editor/")
class EditorPersonController(
        val personService: PersonService
) {

    @GetMapping("/createPerson")
    fun createPerson(model: Model): String {
        model.addAttribute("person", Person())
        return "/editor/addPerson"
    }

    @PostMapping("/addPerson")
    fun addPerson(@Valid person: Person, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.hasErrors())
        }
        personService.create(person)
        return "/editor/addPerson"
    }

}