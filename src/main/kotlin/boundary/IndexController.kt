package boundary

import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Lazy
@Controller
class IndexController {

    @RequestMapping("/")
    fun index(): String {
        return "index.html"
    }

}