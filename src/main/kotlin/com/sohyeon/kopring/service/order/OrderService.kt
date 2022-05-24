package com.sohyeon.kopring.service.order

import com.sohyeon.kopring.dto.order.OrderDto
import com.sohyeon.kopring.entity.order.OrderEntity
import com.sohyeon.kopring.repository.order.OrderRepository

interface OrderService {
    fun getAllOrder(): String
    fun getProductByName(product: String): Result<String>
    fun getProductByInfo(product: String, price: Int): Result<String>
    fun registerOrder(orderDto: OrderDto): OrderEntity
    fun putOrder(id: Long, orderDto: OrderDto): String
    fun deleteOrder(id: Long): Boolean
}