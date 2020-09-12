package com.ataraxia.gabriel_vz.controller.view

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexController {

    @GetMapping("/index")
    fun index(): String {
        return "index"
    }

}