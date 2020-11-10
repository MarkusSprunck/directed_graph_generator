package com.sw.engineering.candies.boundary

import com.sw.engineering.candies.control.DiagramGenerator
import org.apache.commons.io.FilenameUtils
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Lazy
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Lazy
@RestController
class DiagramController {

    private val log = LoggerFactory.getLogger(DiagramController::class.java)

    @GetMapping("/diagram")
    fun diagram(@RequestParam(value = "filter", defaultValue = "") filter: String,
                @RequestParam(value = "file", defaultValue = "data.xlsx") file: String,
                @RequestParam(value = "package", defaultValue = "") componentName: String,
                @RequestParam(value = "showLinks", defaultValue = "false") showLinks: Boolean,
                @RequestParam(value = "showComplex", defaultValue = "true") showComplex: Boolean,
                @RequestParam(value = "strict", defaultValue = "false") strict: Boolean,
                @RequestParam(value = "colorMode", defaultValue = "cluster") colorMode: String,
                @RequestParam(value = "type", defaultValue = "graph") type: String,
                @RequestParam(value = "title", defaultValue = "UML Components") title: String
                ): String {

        log.info("Request Diagram | START - filter=$filter file=$file package=$componentName showLinks=$showLinks showSimple=$showComplex type=$type")

        val result = DiagramGenerator.run(
                FilenameUtils.getName(file),
                filter.replace("-", "|"),
                type,
                componentName,
                showLinks,
                showComplex,
                strict,
                colorMode,
                title)

        log.info("Request Diagram | END   - filter=$filter file=$file package=$componentName showLinks=$showLinks showSimple=$showComplex type=$type")

        return result
    }

}
