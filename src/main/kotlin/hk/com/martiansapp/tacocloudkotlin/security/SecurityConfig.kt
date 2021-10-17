package hk.com.martiansapp.tacocloudkotlin.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter(){

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        // in-memory, good for testing
        auth.inMemoryAuthentication()?.
        withUser("buzz")?.
        password("{noop}infinity")?.
        authorities("ROLE_USER")?.
        and()?.withUser("woody")?.
        password("{noop}bullseye")?.
        authorities("ROLE_USER")
        // in-memory
    }


}