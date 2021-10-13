package hk.com.martiansapp.tacocloudkotlin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

//Spring’s component scanning automatically discovers it and creates an instance of
// Home- Controller as a bean in the Spring application context.

@Controller
class HomeController {

    @GetMapping("/")
    fun home(): String {
        return "home" //spring find a view with name home
    }
}