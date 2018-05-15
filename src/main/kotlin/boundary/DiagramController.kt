package boundary

import control.DiagramGenerator
import org.apache.commons.io.FilenameUtils
import org.springframework.context.annotation.Lazy
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Lazy
@RestController
class DiagramController {

    @GetMapping("/diagram")
    fun diagram(@RequestParam(value = "filter", defaultValue = "") filter: String,
                @RequestParam(value = "file", defaultValue = "data_test.xlsx") file: String): String {

        return DiagramGenerator.run(FilenameUtils.getName(file), filter)
    }

}