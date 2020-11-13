package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.factory.*
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.service.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/editor/")
@Controller
class EditorWorkController(
        val workService: WorkService,
        val workFactory: WorkFactory,
        val placeFactory: PlaceFactory,
        val placeService: PlaceService,
        val textService: TextService,
        val literatureFactory: LiteratureFactory,
        val literatureService: LiteratureService,
        val discographyService: DiscographyService,
        val discographyFactory: DiscographyFactory,
        val personService: PersonService,
        val personFactory: PersonFactory
) : com.ataraxia.gabriel_vz.root.Controller<Model, Work>() {

    @GetMapping("/createWork")
    override fun showAddForm(m: Model): String {
        m.addAttribute("work", Work())
        m.addAttribute("places", placeService.getAll().toOption().orNull())
        m.addAttribute("texts", textService.getAll().toOption().orNull())
        m.addAttribute("literature", literatureService.getAll().toOption().orNull())
        m.addAttribute("discography", discographyService.getAll().toOption().orNull())
        m.addAttribute("persons", personService.getAll().toOption().orNull())
        return "editor/addWork"
    }

    @PostMapping(value = ["/addWork"])
    override fun add(type: Work, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }

        type.apply {
            if (placeOfPremiere?.id == "") {
                this.placeOfPremiere = null
            }
            if (relatedText?.id == "") {
                this.relatedText = null
            }
            if (relatedText?.id == "") {
                this.relatedText = null
            }
            if (incipit?.clef == "") {
                this.incipit = null
            }
        }

        type.literatureList?.removeIf { it.id == null }
        type.literatureList?.map { literatureFactory.entityFromModel(it).relatedWorks?.add(workFactory.entityFromModel(type)) }

        type.discographies?.removeIf { it.id == null }
        type.discographies?.map { discographyFactory.entityFromModel(it).relatedWorks?.add(workFactory.entityFromModel(type)) }

        type.relatedPersons?.removeIf { it.id == null }
        type.relatedPersons?.map { personFactory.entityFromModel(it).relatedWorks?.add(workFactory.entityFromModel(type)) }

        workService.create(type)
        return "redirect:/works"
    }

    @GetMapping("/editWork")
    override fun showUpdateForm(@RequestParam("id") id: String, m: Model): String {
        val work = workService.get(id).toOption().orNull()
        m.addAttribute("work", work)
        return "/editor/editWork"
    }

    @PostMapping("/updateWork/{id}")
    override fun update(@PathVariable("id") id: String, @Valid type: Work): String {
        workService.update(id, type)
        return "redirect:/works"
    }

    @GetMapping("/deleteWork")
    override fun deleteById(@RequestParam("id") id: String): String {
        workService.delete(id)
        return "redirect:/works"
    }

    @GetMapping("/deleteWorks")
    override fun deleteAll(): String {
        workService.deleteAll()
        return "redirect:/works"
    }
}