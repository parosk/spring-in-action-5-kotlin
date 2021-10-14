package hk.com.martiansapp.tacocloudkotlin.web

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


// one way to replace homeController
// another way is to make TacoCloudApplication to implements WebMvcConfigurer,
// and override addViewControllers
@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {

        // replace homeController if it is only view
        registry.addViewController("/").setViewName("home")
    }
}