package com.ataraxia.gabriel_vz.controller.view

import com.ataraxia.gabriel_vz.service.WorkService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/")
class IndexController(
        private val workService: WorkService
) {

    @GetMapping
    fun index(): String {
        return "index"
    }

//    @GetMapping("/works")
//    fun index(model: Model): String {
//        return findPaginated(1, "title", "asc", model)
//    }
//
//    @GetMapping("works/page/{pageNo}")
//    fun findPaginated(@PathVariable("pageNo") pageNo: Int,
//                      @RequestParam("sortField") sortfield: String,
//                      @RequestParam("sortDir") sortDir: String, model: Model): String {
//        val pageSize = 8
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
//        return "works"
//    }

    @GetMapping("/works")
    fun all(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") pageNumber: Int,
            @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
            model: Model): String? {
        model.addAttribute("works", workService.getPage(pageNumber, size))
        return "works2"
    }

}