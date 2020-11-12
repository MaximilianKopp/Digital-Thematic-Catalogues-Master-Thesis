package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.model.Place
import com.ataraxia.gabriel_vz.service.PlaceService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/editor/")
class EditorPlaceController(
        val placeService: PlaceService
) : com.ataraxia.gabriel_vz.root.Controller<Model, Place>() {


    @GetMapping("/createPlace")
    override fun showAddForm(m: Model): String {
        m.addAttribute("place", Place())
        return "editor/addPlace"
    }

    @PostMapping("/addPlace")
    override fun add(@Valid type: Place, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }
        placeService.create(type)
        return "redirect:/places"
    }

    @GetMapping("/editPlace")
    override fun showUpdateForm(@RequestParam("id") id: String, m: Model): String {
        val place = placeService.get(id).toOption().orNull()
        m.addAttribute("place", place)
        return "/editor/editPlace"
    }

    @PostMapping("/updatePlace/{id}")
    override fun update(@PathVariable("id") id: String, @Valid type: Place): String {
        placeService.update(id, type)
        return "redirect:/places"
    }

    @GetMapping("/deletePlace")
    override fun deleteById(@RequestParam("id") id: String): String {
        placeService.delete(id)
        return "redirect:/places"
    }

    @GetMapping("/deletePlaces")
    override fun deleteAll(): String {
        placeService.deleteAll()
        return "redirect:/places"
    }
}