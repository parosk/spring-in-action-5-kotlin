package hk.com.martiansapp.tacocloudkotlin.web

import hk.com.martiansapp.tacocloudkotlin.Ingredient
import hk.com.martiansapp.tacocloudkotlin.Ingredient.Type
import hk.com.martiansapp.tacocloudkotlin.Taco
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*
import javax.validation.Valid


@Controller //this annotation is for component scanning, auto create instance of DesignTacoController as bean
@RequestMapping("/design") // handle request whose path begins with /design
class DesignTacoController {
    private val logger = LoggerFactory.getLogger(this.javaClass)



    //@ModelAttribute at method level: the method are invoked before the controller methods
    //annotated with @RequestMapping are invoked
    //so ingredients are added to design form at  PostMapping
    // it is like @Bean + @Before
    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients = listOf(
                Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                Ingredient("CARN", "Carnitas", Type.PROTEIN),
                Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                Ingredient("LETC", "Lettuce", Type.VEGGIES),
                Ingredient("CHED", "Cheddar", Type.CHEESE),
                Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                Ingredient("SLSA", "Salsa", Type.SAUCE),
                Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        )
        val types = Type.values()
        for (type in types) {
            model.addAttribute(type.name.lowercase(Locale.getDefault()),
                    filterByType(ingredients, type))
        }
    }

    @GetMapping
    fun showDesignForm(model: Model): String? {
        model.addAttribute("design", Taco())
        return "design"
    }

    @PostMapping
    //the @Valid annotation check for validation for the design:Taco
    //@ModelAttribute at argument level : When used as a method argument,
    // it indicates the argument should be retrieved from the model.
    // When not present, it should be first instantiated and then added
    // to the model and once present in the model, the arguments fields should be
    // populated from all request parameters that have matching names.
    // it is like @Autowired + @Qualifier
    fun processDesign(@Valid @ModelAttribute("design") design: Taco, errors: Errors, model: Model): String? {

        if (errors.hasErrors()) {
            return "design"
        }

        // Save the taco design...
        // We'll do this in chapter 3
        logger.info("Processing design: $design")
        return "redirect:/orders/current"  // redirect to another view
    }

    private fun filterByType(
            ingredients: List<Ingredient>, type: Type): List<Ingredient> {
        return ingredients
                .filter { it.type == type }
    }
}