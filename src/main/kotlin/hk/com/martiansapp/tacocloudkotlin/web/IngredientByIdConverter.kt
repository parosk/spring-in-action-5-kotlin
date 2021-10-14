package hk.com.martiansapp.tacocloudkotlin.web

import hk.com.martiansapp.tacocloudkotlin.Ingredient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component


// find ingredient by id, without it process design dont work
@Component
class IngredientByIdConverter @Autowired constructor() : Converter<String, Ingredient?> {

    override fun convert(source: String): Ingredient? {

        val ingredients = listOf(
                Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        )
        return ingredients.first { it.id == source }

    }



}