package com.ataraxia.gabriel_vz.controller.view

import arrow.core.getOrElse
import arrow.core.right
import com.ataraxia.gabriel_vz.service.WorkService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/")
class IndexController(
        private val workService: WorkService
) {

    @GetMapping("/works2")
    fun index2(): String {
        return "index3"
    }

//    @GetMapping("/works")
//    fun index(model: Model): String {
//        return findPaginated(1,"title", "asc", model)
//    }

//    @GetMapping("works/page/{pageNo}")
//    fun findPaginated(@PathVariable("pageNo") pageNo: Int,
//                      @RequestParam("sortField") sortfield: String,
//                      @RequestParam("sortDir") sortDir: String, model: Model): String {
//        val pageSize = 10
//
//        val page = workService.findPaginated(pageNo, pageSize, sortfield, sortDir)
//        val works = page.content
//        model.addAttribute("currentPage", pageNo)
//        model.addAttribute("totalPages", page.totalPages)
//        model.addAttribute("totalItems", page.totalElements)
//        model.addAttribute("sortField", sortfield)
//        model.addAttribute("sortDir", sortDir)
//        model.addAttribute("reverseSortDir",
//                when {
//                    sortDir.equals("asc") -> "desc"
//                    else -> "asc"
//                })
//
//        model.addAttribute("works", works)
//        return "index3"
//    }

}