package com.ataraxia.gabriel_vz.root

import com.ataraxia.gabriel_vz.model.Place
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.servlet.ModelAndView

@Controller
abstract class ViewController<M> {

    abstract fun all(m: Model): String

    abstract fun one(id: String): String

    abstract fun add(m: M): String

    abstract fun update(id: String, m: M): String

    abstract fun delete(id: String): String

    abstract fun deleteAll(): String
}