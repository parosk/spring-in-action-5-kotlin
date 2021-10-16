package hk.com.martiansapp.tacocloudkotlin.data

import hk.com.martiansapp.tacocloudkotlin.Ingredient

interface IngredientRepository {
    fun findAll(): Iterable<Ingredient>
    fun findOne(id: String): Ingredient?
    fun save(ingredient: Ingredient): Ingredient
}
