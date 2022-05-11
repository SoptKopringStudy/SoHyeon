package com.sohyeon.kopring.controller

import com.sohyeon.kopring.dto.UserDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// localhost:8080/mapping
@RestController
@RequestMapping("/mapping")
class MappingController {
    @PostMapping("")
    fun postUser(@RequestBody userDto: UserDto) = userDto.name

    @PutMapping("")
    fun putUser(@RequestBody userDto: UserDto) = userDto

    @DeleteMapping("")
    fun deleteUser(@RequestBody userDto: UserDto) = "삭제에 성공하였습니다."
}