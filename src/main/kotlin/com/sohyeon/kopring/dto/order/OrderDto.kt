package com.sohyeon.kopring.dto.order

import com.sohyeon.kopring.entity.order.OrderEntity

data class OrderDto(
        val product: String,
        val price: Int
) {
    fun toOrder(id: Int) = OrderEntity (
            id = id.toLong(),
            product = product,
            price = price
            )
}
