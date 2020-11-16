package com.ataraxia.gabriel_vz.controller.editor

import com.ataraxia.gabriel_vz.factory.IncipitFactory
import com.ataraxia.gabriel_vz.model.Incipit
import com.ataraxia.gabriel_vz.service.IncipitService
import com.ataraxia.gabriel_vz.service.MEIService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@Controller
@RequestMapping("/editor/")
class EditorIncipitController(
        val incipitService: IncipitService,
        val meiService: MEIService
) {

    @GetMapping("/editIncipit")
    fun showUpdateForm(@RequestParam("id") id: String, m: Model): String {
        val incipit = incipitService.get(id).orNull()
        m.addAttribute("incipit", incipit)
        return "editor/editIncipit"
    }

    @PostMapping("/updateIncipit/{id}")
    fun update(@PathVariable("id") id: String, @Valid type: Incipit): String {
        incipitService.update(id, type)
        return "redirect:/incipits"
    }

    @PostMapping("/downloadMEI")
    fun getMei(@RequestParam("id") id: String, model: Model): String {

        val bw = BufferedWriter(FileWriter("test.mei"))
        val incipit = incipitService.get(id).orNull()
        val data = meiService.paeToMei(incipit)
        bw.write(data)
        bw.close()
        return "redirect:/editor/incipits/download/test.mei"
    }

    @GetMapping("/incipits/download/{fileName:.+}")
    fun downloadMEI(@PathVariable("fileName") fileName: String, @RequestHeader referer: String,
                    response: HttpServletResponse) {
        if (referer != null && !referer.isEmpty()) {
            println("Download permitted")
        }

        val file = Paths.get(fileName)
        if (Files.exists(file)) {
            response.contentType = "APPLICATION/OCTET-STREAM"
            response.addHeader("Content-Disposition", "attachment; filename=$fileName")
        }

        try {
            Files.copy(file, response.outputStream)
            response.outputStream.flush()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

    }

}