package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.factory.WorkFactory
import com.ataraxia.gabriel_vz.model.Work
import com.ataraxia.gabriel_vz.persistence.WorkEntity
import com.ataraxia.gabriel_vz.repository.WorkRepository
import com.ataraxia.gabriel_vz.service.WorkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@RequestMapping("/editor/")
@Controller
class EditorWorkController(
        val workService: WorkService
) {
    @Autowired
    lateinit var workRepository: WorkRepository
    @Autowired
    lateinit var workFactory: WorkFactory

    @GetMapping("/create")
    fun createWork(work: Work, model: Model): String {
        model.addAttribute("work", work)
        return "editor/addWork"
    }

    @PostMapping("/add")
    fun addWork(@Valid work: Work, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "common_user/workdetails"
        }
        //workService.create(work)



        workRepository.save(workFactory.entityFromModel(work))
        return "editor/addWork"
    }
}