package com.sohyeon.kopring.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// localhost:8080/
@RestController
class SampleController {
    // GET localhost:8080/
    @GetMapping("")
    fun printHelloWorld() = "Hello World!"

    // GET localhost:8080/introduce
    @GetMapping("/introduce")
    fun printMyself() = "저는 김소현이고 23살입니다."

    /**
     * 1. Path
     * 2. Query
     * 3. RequestBody -> POST, PUT, DELETE
     */

    @GetMapping("/introduce/{name}")
    fun printName(
            @PathVariable("name") name: String
    ) = "이름: $name"

    @GetMapping("/introduce-param")
    fun printByQuery(
            @RequestParam("age") age: Int,
            @RequestParam("name") name: String
    ) = "이름: $name, 나이: $age"

}