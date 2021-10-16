package hk.com.martiansapp.tacocloudkotlin.data


import hk.com.martiansapp.tacocloudkotlin.Ingredient
import hk.com.martiansapp.tacocloudkotlin.Taco
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.PreparedStatementCreatorFactory
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.sql.Types
import java.util.*


@Repository
class JdbcTacoRepository(val jdbc: JdbcTemplate) : TacoRepository {
    override fun save(design: Taco): Taco {
        val tacoId = saveTacoInfo(design)
        design.id = tacoId
        design.ingredients.forEach {
            saveIngredientToTaco(it, tacoId)
        }
        return design
    }

    private fun saveTacoInfo(taco: Taco): Long {
        taco.createdAt = Date()
        val psc: PreparedStatementCreator = PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(
                listOf(
                        taco.name,
                        Timestamp((taco.createdAt as Date).time)))
        val keyHolder: KeyHolder = GeneratedKeyHolder() // use to get the raw generated id
        jdbc.update(psc, keyHolder)
        return keyHolder.key!!.toLong()

    }

    private fun saveIngredientToTaco(
            ingredient: Ingredient, tacoId: Long) {
        jdbc.update(
                "insert into Taco_Ingredients (taco, ingredient) " +
                        "values (?, ?)",
                tacoId, ingredient.id)
    }


}