package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.factory.*
import com.ataraxia.gabriel_vz.model.Discography
import com.ataraxia.gabriel_vz.model.Literature
import com.ataraxia.gabriel_vz.model.Person
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.repository.WorkRepository
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

    lateinit var workRepository: WorkRepository

    @GetMapping("/createWork")
    override fun showAddForm(m: Model): String {
        m.addAttribute("work", Work())
        m.addAttribute("places", placeService.getAll().orNull())
        m.addAttribute("texts", textService.getAll().orNull())
        m.addAttribute("literature", literatureService.getAll().orNull())
        m.addAttribute("discography", discographyService.getAll().orNull())
        m.addAttribute("persons", personService.getAll().orNull())
        return "editor/addWork"
    }

    @PostMapping(value = ["/addWork"])
    override fun add(type: Work, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }

        type.apply {
            if (placeOfPremiere?.id == "")
                this.placeOfPremiere = null
            if (relatedText?.id == "")
                this.relatedText = null
            if (relatedText?.id == "")
                this.relatedText = null
            if (incipit?.clef == "")
                this.incipit = null
        }

        type.relatedLiterature?.removeIf { it.id == null }
        type.relatedLiterature?.map { literatureFactory.entityFromModel(it).relatedWorks?.add(workFactory.entityFromModel(type)) }

        type.discographies?.removeIf { it.id == null }
        type.discographies?.map { discographyFactory.entityFromModel(it).relatedWorks?.add(workFactory.entityFromModel(type)) }

        type.relatedPersons?.removeIf { it.id == null }
        type.relatedPersons?.map { personFactory.entityFromModel(it).relatedWorks?.add(workFactory.entityFromModel(type)) }

        workService.create(type)
        return "redirect:/works"
    }

    @GetMapping("/editWork")
    override fun showUpdateForm(@RequestParam("id") id: String, m: Model): String {
        val work = workService.get(id).orNull()
        m.addAttribute("work", work)
        m.addAttribute("places", placeService.getAll().orNull())
        m.addAttribute("texts", textService.getAll().orNull())
        m.addAttribute("literature", literatureService.getAll().orNull())
        m.addAttribute("discography", discographyService.getAll().orNull())
        m.addAttribute("persons", personService.getAll().orNull())
        return "/editor/editWork"
    }

    @PostMapping("/updateWork/{id}")
    override fun update(@PathVariable("id") id: String, @Valid type: Work): String {
        val updatedLiterature = mutableListOf<Literature>()
        val updatedDiscography = mutableListOf<Discography>()
        val updatedPersons = mutableListOf<Person>()

        if (type.placeOfPremiere?.id == "") type.placeOfPremiere = null
        else type.placeOfPremiere = placeService.get(type.placeOfPremiere?.id!!).orNull()
        if (type.relatedText?.id == "") type.relatedText = null
        else type.relatedText = textService.get(type.relatedText?.id!!).orNull()
        if (type.incipit?.clef == "") type.incipit = null

        if (type.relatedLiterature?.removeIf { it.id == null }!!) type.relatedLiterature = null
        else type.relatedLiterature!!.forEach { item ->
            literatureService.get(item.id!!).orNull()?.let { updatedLiterature.add(it) }
        }

        if (type.discographies?.removeIf { it.id == null }!!) type.discographies = null
        else type.discographies!!.forEach { item ->
            discographyService.get(item.id!!).orNull()?.let { updatedDiscography.add(it) }
        }

        if (type.relatedPersons?.removeIf { it.id == null }!!) type.relatedPersons = null
        else type.relatedPersons!!.forEach { item ->
            personService.get(item.id!!).orNull()?.let { updatedPersons.add(it) }
        }

        type.relatedLiterature = updatedLiterature
        type.discographies = updatedDiscography
        type.relatedPersons = updatedPersons

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