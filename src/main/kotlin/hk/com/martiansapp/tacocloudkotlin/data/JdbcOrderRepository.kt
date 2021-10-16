package hk.com.martiansapp.tacocloudkotlin.data

import com.fasterxml.jackson.databind.ObjectMapper
import hk.com.martiansapp.tacocloudkotlin.Order
import hk.com.martiansapp.tacocloudkotlin.Taco
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.HashMap

@Repository
class JdbcOrderRepository(jdbc: JdbcTemplate) : OrderRepository {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val orderInserter = SimpleJdbcInsert(jdbc).withTableName("Taco_Order")
            .usingGeneratedKeyColumns("id")
    private val orderTacoInserter = SimpleJdbcInsert(jdbc)
            .withTableName("Taco_Order_Tacos")
    private val objectMapper = ObjectMapper()


    fun saveOrderDetails(order: Order): Long {
        val values: MutableMap<String, Any?> = objectMapper.convertValue(order, MutableMap::class.java) as MutableMap<String, Any?>
        values["placedAt"] = order.placedAt
        return orderInserter
                .executeAndReturnKey(values)
                .toLong()
    }

    fun saveTacoToOrder(taco: Taco, orderId: Long) {
        // save it to the order taco table
        val values: MutableMap<String, Any?> = HashMap()
        values["tacoOrder"] = orderId
        values["taco"] = taco.id
        orderTacoInserter.execute(values)
    }

    override fun save(order: Order): Order {
        order.placedAt = Date()
        val orderId: Long = saveOrderDetails(order)
        order.id = orderId
        val tacos: List<Taco> = order.tacos

        for (taco in tacos) {
            saveTacoToOrder(taco, orderId)
        }
        return order
    }


}