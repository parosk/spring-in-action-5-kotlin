package hk.com.martiansapp.tacocloudkotlin.web

import hk.com.martiansapp.tacocloudkotlin.Ingredient
import hk.com.martiansapp.tacocloudkotlin.Ingredient.Type
import hk.com.martiansapp.tacocloudkotlin.Order
import hk.com.martiansapp.tacocloudkotlin.Taco
import hk.com.martiansapp.tacocloudkotlin.data.IngredientRepository
import hk.com.martiansapp.tacocloudkotlin.data.TacoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

// this annotation is for component scanning, auto create instance of DesignTacoController as bean
@Controller
// handle request whose path begins with /design
@RequestMapping("/design")
// the order object should be kept in session and available across multiple requests
@SessionAttributes("order")
class DesignTacoController(@Autowired val ingredientRepo: IngredientRepository, @Autowired val designRepo: TacoRepository) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    // ensures that an Order object will be created in the model
    @ModelAttribute(name = "order")
    fun order(): Order? {
        return Order()
    }

    // ensures that an design object will be created in the model
    @ModelAttribute(name = "taco")
    fun taco(): Taco? {
        return Taco()
    }
    //@ModelAttribute at method level: the method are invoked before the controller methods
    //annotated with @RequestMapping are invoked
    //so ingredients are added to design form at  PostMapping
    // it is like @Bean + @Before
    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients = ingredientRepo.findAll().toMutableList()
        val types = Type.values()
        for (type in types) {
            model.addAttribute(type.name.lowercase(Locale.getDefault()),
                    filterByType(ingredients, type))
        }
    }

    @GetMapping
    fun showDesignForm(model: Model): String? {
        return "design"
    }

    //the @Valid annotation check for validation for the design:Taco
    //@ModelAttribute at argument level : When used as a method argument,
    // it indicates the argument should be retrieved from the model.
    // When not present, it should be first instantiated and then added
    // to the model and once present in the model, the arguments fields should be
    // populated from all request parameters that have matching names.
    // it is like @Autowired + @Qualifier
    @PostMapping
    fun processDesign(@Valid @ModelAttribute taco: Taco, errors: Errors, @ModelAttribute order: Order): String? {

        if (errors.hasErrors()) {
            return "design"
        }
        val saved = designRepo.save(taco)
        order.addDesign(saved)
        // at is point, Order object remains in session and isn't saved in db until complication of order form
        return "redirect:/orders/current"  // redirect to another view
    }

    private fun filterByType(
            ingredients: List<Ingredient>, type: Type): List<Ingredient> {
        return ingredients
                .filter { it.type == type }
    }
}