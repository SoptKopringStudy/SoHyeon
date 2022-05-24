package com.sohyeon.kopring.service.order

import com.sohyeon.kopring.dto.order.OrderDto
import com.sohyeon.kopring.entity.order.OrderEntity
import com.sohyeon.kopring.repository.order.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl(
        private val repository: OrderRepository
): OrderService {
    private val orderList = mutableListOf<OrderEntity>()

    // 모든 정보 가져오기
    override fun getAllOrder(): String {
        val allOrderList = repository.findAll()
        print(allOrderList)

        if (allOrderList.isEmpty()) return "상품이 없습니다."
        else return allOrderList.joinToString { it.product }
    }

    // 파라미터로 정보 가져오기
    override fun getProductByName(product: String): Result<String> {
        return when (val order = repository.findOrderEntityByProduct(product)) {
            null -> Result.failure(IllegalStateException("${product}가 없습니다."))
            else -> Result.success(order.getInfo())
        }
    }

    // 상품명+가격으로 정보 가져오기
    override fun getProductByInfo(product: String, price: Int): Result<String> {
        return when (repository.findOrderEntityByProductAndPrice(product, price)) {
            null -> Result.failure(IllegalStateException("상품 조회 실패"))
            else -> Result.success("${price}원인 ${product}(이)가 존재합니다.")
        }
    }

    // [POST] 데이터 insert
    @Transactional
    override fun registerOrder(orderDto: OrderDto): OrderEntity {
        val order = orderDto.toOrder()
        repository.save(order)

        return order
    }

    // [PUT] 데이터 update
    @Transactional
    override fun putOrder(id: Long, orderDto: OrderDto): String {
        return when (val orderResult = repository.findOrderEntityById(id)) {
            null -> {
                repository.save(orderDto.toOrder())
                "${orderDto.price} 등록 성공"
            }
            else -> {
                orderResult.product = orderDto.product
                orderResult.price = orderDto.price
                "${orderDto.product} 수정 성공"
            }
        }
    }

    // [DELETE] 데이터 delete
    @Transactional
    override fun deleteOrder(id: Long): Boolean {
        return when (repository.findOrderEntityById(id)) {
            null -> false
            else -> {
                repository.deleteOrderEntityById(id)
                true
            }
        }
    }
}