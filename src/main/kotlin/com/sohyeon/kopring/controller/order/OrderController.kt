package com.sohyeon.kopring.controller.order

import com.sohyeon.kopring.dto.order.OrderDto
import com.sohyeon.kopring.entity.BaseResponse
import com.sohyeon.kopring.entity.order.OrderEntity
import com.sohyeon.kopring.service.order.OrderService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/order")
@RestController
class OrderController(
        private val orderService: OrderService
) {
    @GetMapping("")
    fun getAllOrders() = orderService.getAllOrder()

    @GetMapping("/product/{product}")
    fun getOrderByProduct(@PathVariable("product") product: String): BaseResponse<String> {
        orderService.getProductByName(product)
                .onSuccess { return BaseResponse.success<String>(it) }
                .onFailure { return BaseResponse.failure((it.message ?: "해당 상품이 없습니다.")) }
        throw RuntimeException("Unreachable Code")
    }

    @GetMapping("/info")
    fun getOrderByInfo(
            @RequestParam("product") product: String,
            @RequestParam("price") price: Int
    ): BaseResponse<String> {
        orderService.getProductByInfo(product, price)
                .onSuccess { return BaseResponse.success<String>(it) }
                .onFailure { return BaseResponse.failure(it.message ?: "해당 상품이 없습니다.") }
        throw java.lang.RuntimeException("Unreachable Code")
    }

    @PostMapping("")
    fun registerOrder(@RequestBody orderDto: OrderDto): OrderEntity = orderService.registerOrder(orderDto)

    @PutMapping("/{id}")
    fun putOrder(@PathVariable("id") id: Long, @RequestBody orderDto: OrderDto) = orderService.putOrder(id, orderDto)

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable("id") id: Long) = orderService.deleteOrder(id)
}