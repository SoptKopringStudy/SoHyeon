package com.sohyeon.kopring.service.order

import com.sohyeon.kopring.dto.order.OrderDto
import com.sohyeon.kopring.entity.order.OrderEntity
import com.sohyeon.kopring.repository.order.OrderRepository
import org.springframework.stereotype.Service

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
        return when (val order = orderList.find { it.isProductSame(product) }) {
            null -> Result.failure(IllegalStateException("${product}가 없습니다."))
            else -> Result.success(order.getInfo())
        }
    }

    // 상품명+가격으로 정보 가져오기
    override fun getProductByInfo(product: String, price: Int): Result<String> {
        return when (val product = orderList.find { it.isOrderInfo(product, price) }) {
            null -> Result.failure(IllegalStateException("상품 조회 실패"))
            else -> Result.success("${price}원인 ${product}이 존재합니다.")
        }
    }

    // [POST] 데이터 insert
    override fun registerOrder(orderDto: OrderDto): OrderEntity {
        val order = orderDto.toOrder(orderList.size)
        orderList.add(order) // insert

        return order
    }

    // [PUT] 데이터 update
    override fun putOrder(orderEntity: OrderEntity): Result<String> {
        return when (val order = orderList.find { it.isIdEqual(orderEntity.id) }) {
            null -> Result.failure(IllegalStateException("수정 실패"))
            else -> Result.success("수정 성공")
        }
    }

    // [DELETE] 데이터 delete
    override fun deleteOrder(id: Int): Result<String> {
        return when (val order = orderList.find { it.isIdEqual((id.toLong())) }) {
            null -> Result.failure(IllegalStateException("삭제 실패"))
            else -> {
                orderList.removeIf { it.isIdEqual(id.toLong()) }
                Result.success("삭제 성공")
            }
        }
    }
}