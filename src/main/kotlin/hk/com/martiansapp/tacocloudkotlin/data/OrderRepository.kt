package hk.com.martiansapp.tacocloudkotlin.data

import hk.com.martiansapp.tacocloudkotlin.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Long> {

    /**some example of additional operation
    fun findByZip(zip: String?): List<Order?>?
    fun readOrdersByDeliveryZipAndPlacedAtBetween(
    deliveryZip: String?, startDate: Date?, endDate: Date?): List<Order?>?
     */

    /**
     * example for custom query
    @Query("Order o where o.deliveryCity='Seattle'")
    fun readOrdersDeliveredInSeattle(): List<Order?>?
     */
}
/**
keyword that jpa can recognize and generate method
IsAfter, After, IsGreaterThan, GreaterThan
IsGreaterThanEqual, GreaterThanEqual
IsBefore, Before, IsLessThan, LessThan
IsLessThanEqual, LessThanEqual
IsBetween, Between
IsNull, Null
IsNotNull, NotNull
IsIn, In
IsNotIn, NotIn
IsStartingWith, StartingWith, StartsWith
IsEndingWith, EndingWith, EndsWith
IsContaining, Containing, Contains
IsLike, Like
IsNotLike, NotLike
IsTrue, True
IsFalse, False
Is, Equals
IsNot, Not
IgnoringCase, IgnoresCase
 */