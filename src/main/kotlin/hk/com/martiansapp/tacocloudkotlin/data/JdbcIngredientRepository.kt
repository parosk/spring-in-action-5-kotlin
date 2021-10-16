package hk.com.martiansapp.tacocloudkotlin.data

import hk.com.martiansapp.tacocloudkotlin.Ingredient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.SQLException

//stereotype annotation that spring defines
@Repository
class JdbcIngredientRepository(val jdbc: JdbcTemplate) : IngredientRepository {

    override fun findAll(): Iterable<Ingredient> {
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient).filterNotNull().asIterable()
    }

    override fun findOne(id: String): Ingredient? {
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id)
    }

    override fun save(ingredient: Ingredient): Ingredient {
        jdbc.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.id,
                ingredient.name,
                ingredient.type)
        return ingredient
    }

    @Throws(SQLException::class)
    fun mapRowToIngredient(rs: ResultSet, rowNum: Int): Ingredient? {
        return Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")))
    }
}