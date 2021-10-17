package hk.com.martiansapp.tacocloudkotlin.security

import hk.com.martiansapp.tacocloudkotlin.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/register")
class RegistrationController(@Autowired val userRepo: UserRepository, @Autowired val passwordEncoder: PasswordEncoder) {

    @GetMapping
    fun registerForm(): String? {
        return "registration"
    }

    @PostMapping
    fun processRegistration(form: RegistrationForm): String? {
        userRepo.save(form.toUser(passwordEncoder))
        return "redirect:/login"
    }

}