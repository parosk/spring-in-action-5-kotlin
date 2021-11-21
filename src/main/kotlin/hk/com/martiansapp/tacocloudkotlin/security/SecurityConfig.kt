package hk.com.martiansapp.tacocloudkotlin.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.StandardPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig(@Autowired val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter() {


    //web level security config
    // set custom login page, additional security conditions before allowing a request
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        // the order is important, guard the /design and /order, permit all other
        /**
        common method
        access(String)
        anonymous()
        authenticated()
        denyAll()
        fullyAuthenticated()
        hasAnyAuthority(String...)
        hasAnyRole(String...)
        hasAuthority(String)
        hasIpAddress(String)
        asRole(String)
        not()
        permitAll()
        rememberMe()
         */

        /**
         * example for crazier rule
         * .antMatchers("/design", "/orders")
           .access("hasRole('ROLE_USER') && " +
        "T(java.util.Calendar).getInstance().get("+
        "T(java.util.Calendar).DAY_OF_WEEK) == " +
        "T(java.util.Calendar).TUESDAY")
         */

        http
                .authorizeRequests()
                .antMatchers("/design", "/orders")
                .hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .antMatchers("/h2_console/**").permitAll()
                /** indicating finish authorization configuration, and ready to apply some additional http config*/
                .and()
                .formLogin()
                /** unauthorized request are redirected here, by default*/
                .loginPage("/login")
                 /**spring expects that the username and password fields be named user- name and password
                 * but can be configurated, by loginProcessingUrl / usernameParameter / passwordParameter */
                //.loginProcessingUrl("/authenticate")
                //.usernameParameter("user")
                //.passwordParameter("pwd")
                /**set default page after login, by default is the root path,
                * 2 nd parameter force to design page, even was in other page before */
                //.defaultSuccessUrl("/design", true)
                /**enable logout*/
                .and()
                .logout()
                .logoutSuccessUrl("/")
                /**to disable CSRF (but please dont do it, but the above setting cant post to design/orders if dont disable it  ) */
                .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**").ignoringAntMatchers("/**")
                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()


//        // this will ignore only h2-console csrf, spring security 4+
//        http.csrf().ignoringAntMatchers("/h2-console/**")
//        //this will allow frames with same origin which is much more safe
//        http.headers().frameOptions().sameOrigin()

    }


    // this configure method handle what auth to use
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        // in-memory, good for testing
        // noop is no encryption
//        auth.inMemoryAuthentication()?.
//        withUser("buzz")?.
//        password("{noop}infinity")?.
//        authorities("ROLE_USER")?.
//        and()?.withUser("woody")?.
//        password("{noop}bullseye")?.
//        authorities("ROLE_USER")
        // in-memory

        // skipping jdbcAuthentication and LDAP
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder())

    }
    //Any calls to encoder()will then be intercepted to return the bean instance
    // from the application context.
    @Bean
    fun encoder(): PasswordEncoder? {
        return StandardPasswordEncoder("53cr3t")
    }



}