package com.sohyeon.kopring.dto.order

import com.sohyeon.kopring.entity.order.OrderEntity

data class OrderDto(
        val product: String,
        val price: Int
) {
    fun toOrder() = OrderEntity (
            product = product,
            price = price
            )
}
