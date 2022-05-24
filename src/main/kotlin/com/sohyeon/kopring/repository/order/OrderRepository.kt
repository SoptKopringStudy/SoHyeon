package com.sohyeon.kopring.repository.order

import com.sohyeon.kopring.entity.order.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * 1. Entity
 * 2. Primary Key 데이터 타입 (id)
 */
interface OrderRepository : JpaRepository<OrderEntity, Long> {
    fun findOrderEntityByProduct(product: String): OrderEntity?
    fun findOrderEntityByProductAndPrice(product: String, price: Int): OrderEntity?
    fun findOrderEntityById(Id: Long): OrderEntity?
    fun deleteOrderEntityById(Id: Long)
}