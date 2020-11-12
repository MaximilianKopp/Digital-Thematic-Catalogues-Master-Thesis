package com.ataraxia.gabriel_vz.root

import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult

@Controller
abstract class Controller<M, T> {

    abstract fun showAddForm(m: M): String

    abstract fun add(type: T, bindingResult: BindingResult): String

    abstract fun showUpdateForm(id: String, m: M): String

    abstract fun update(id: String, type: T): String

    abstract fun deleteById(id: String): String

    abstract fun deleteAll(): String
}