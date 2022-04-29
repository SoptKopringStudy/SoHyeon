package com.example.kopring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Problem3

// 구매 물품 data class
data class Product(val name: String, val price: Int)

// 영수증 출력 class
class Receipt(val products: List<Product>){

    fun printReceipt() {
        var total: Int = 0

        this.products.map {
            println("${it.name} ${it.price}원")
            total += it.price
        }

        println("====================")
        println("총합 ${total}원")
    }
}

fun main() {
    val products = listOf(
        Product("돼지목살", 6000),
        Product("제로콜라", 2000),
        Product("민트초코오레오", 1500),
        Product("진라면순한맛", 1000)
    )

    Receipt(products).printReceipt()
}
