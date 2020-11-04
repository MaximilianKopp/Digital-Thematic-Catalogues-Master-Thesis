package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.factory.PlaceFactory
import com.ataraxia.gabriel_vz.factory.WorkFactory
import com.ataraxia.gabriel_vz.model.Place
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.service.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/editor/")
class EditorPlaceController(
        val placeService: PlaceService
) {


    @GetMapping("/createPlace")
    fun placeForm(model: Model): String {
        model.addAttribute("place", Place())
        return "editor/addPlace"
    }

    @PostMapping("/addPlace")
    fun addPlace(@Valid place: Place, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            print(bindingResult.allErrors)
        }
        placeService.create(place)
        return "editor/addPlace"
    }


}