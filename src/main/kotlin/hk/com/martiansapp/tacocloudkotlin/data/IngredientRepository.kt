package hk.com.martiansapp.tacocloudkotlin.data

import hk.com.martiansapp.tacocloudkotlin.Ingredient
import org.springframework.data.repository.CrudRepository

//first para : data table in persist table
//second para : data type for id
//CrudRepository declares about a dozen methods for CRUD
interface IngredientRepository : CrudRepository<Ingredient, String>