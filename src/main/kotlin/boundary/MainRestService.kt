package boundary

import control.DiagramGenerator
import org.apache.commons.io.FilenameUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MainRestService {

    @GetMapping("/diagram")
    fun diagram(@RequestParam(value = "filter", defaultValue = ""              ) filter: String,
                @RequestParam(value = "file",   defaultValue = "data_test.xlsx") file  : String) : String {

        val saveFileName = FilenameUtils.getName(file)

        return DiagramGenerator.run(saveFileName, filter)
    }

}
