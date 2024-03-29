package com.sw.engineering.candies.boundary

import com.sw.engineering.candies.control.DiagramGenerator
import org.apache.commons.io.FilenameUtils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger.getLogger

@Lazy
@RestController
class DiagramController @Autowired constructor(var diagramGenerator: DiagramGenerator) {

    private val log = getLogger(DiagramController::class.java.typeName)

    @GetMapping("/diagram")
    fun diagram(
        @RequestParam(value = "filter", defaultValue = "") filter: String,
        @RequestParam(value = "file", defaultValue = "data.xlsx") file: String,
        @RequestParam(value = "package", defaultValue = "") componentName: String,
        @RequestParam(value = "showLinks", defaultValue = "false") showLinks: Boolean,
        @RequestParam(value = "showComplex", defaultValue = "true") showComplex: Boolean,
        @RequestParam(value = "strict", defaultValue = "false") strict: Boolean,
        @RequestParam(value = "colorMode", defaultValue = "cluster") colorMode: String,
        @RequestParam(value = "type", defaultValue = "graph") type: String,
        @RequestParam(value = "title", defaultValue = "") title: String
    ): String {

        log.finer("Request Diagram | START - filter=$filter file=$file package=$componentName showLinks=$showLinks showSimple=$showComplex type=$type")

        val result = diagramGenerator.run(
            FilenameUtils.getName(file),
            filter.replace("-", "|"),
            type,
            componentName,
            showLinks,
            showComplex,
            strict,
            colorMode,
            title
        )

        log.finer("Request Diagram | END   - filter=$filter file=$file package=$componentName showLinks=$showLinks showSimple=$showComplex type=$type")

        return result
    }

}
