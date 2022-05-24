package com.sohyeon.kopring.entity.order

import javax.persistence.*

@Entity
@Table(name="orders")
class OrderEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        @Column(name="product") var product: String = "",
        @Column(name = "price") var price: Int = 0
        ) {

    // 빈 생성자
    constructor(): this(product="", price=0)



    fun getInfo() = "상품: ${product}, 가격: ${price}"
    fun isProductSame(product: String) = product==product
    fun isOrderInfo(product: String, price: Int) = product==product && price==price
    fun isIdEqual(id: Long) = id==id


}