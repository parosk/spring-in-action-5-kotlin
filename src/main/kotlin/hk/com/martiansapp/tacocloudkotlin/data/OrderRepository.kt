package hk.com.martiansapp.tacocloudkotlin.data

import hk.com.martiansapp.tacocloudkotlin.Order

interface OrderRepository {
    fun save(order: Order): Order
}