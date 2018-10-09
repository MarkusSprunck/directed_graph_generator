package boundary

import control.DiagramGenerator
import org.apache.commons.io.FilenameUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.context.annotation.Lazy
import org.springframework.web.bind.annotation.RequestParam

@Lazy
@RestController
class DiagramController {

    @GetMapping("/diagram")
    fun diagram(@RequestParam(value = "filter", defaultValue = "") filter: String,
                @RequestParam(value = "file", defaultValue = "data2.xlsx") file: String,
                @RequestParam(value = "package", defaultValue = "") componentName: String ,
                @RequestParam(value = "type", defaultValue = "graph") type: String): String {

        return DiagramGenerator.run(FilenameUtils.getName(file), filter, type, componentName)
    }

}
