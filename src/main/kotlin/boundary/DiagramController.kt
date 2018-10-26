package boundary

import control.DiagramGenerator
import org.apache.commons.io.FilenameUtils
import org.springframework.context.annotation.Lazy
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@Lazy
@RestController
class DiagramController {

    private val log = Logger.getLogger(DiagramController::class.java.name)

    @GetMapping("/diagram")
    fun diagram(@RequestParam(value = "filter", defaultValue = "") filter: String,
                @RequestParam(value = "file", defaultValue = "data.xlsx") file: String,
                @RequestParam(value = "package", defaultValue = "") componentName: String,
                @RequestParam(value = "showLinks", defaultValue = "false") showLinks: Boolean,
                @RequestParam(value = "strict", defaultValue = "false") strict: Boolean,
                @RequestParam(value = "colorMode", defaultValue = "cluster") colorMode: String,
                @RequestParam(value = "type", defaultValue = "graph") type: String): String {

        log.info("Request Diagram | START - filter=$filter file=$file package=$componentName showLinks=$showLinks  type=$type")

        val result = DiagramGenerator.run(
                FilenameUtils.getName(file),
                filter.replace("-", "|"),
                type,
                componentName,
                showLinks,
                strict,
                colorMode)

        log.info("Request Diagram | END   - filter=$filter file=$file package=$componentName showLinks=$showLinks  type=$type")

        return result
    }

}
