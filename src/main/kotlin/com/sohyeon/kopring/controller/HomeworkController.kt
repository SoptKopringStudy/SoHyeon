package com.sohyeon.kopring.controller

import com.sohyeon.kopring.entity.HomeworkUser
import com.sohyeon.kopring.dto.HomeworkUserDto
import com.sohyeon.kopring.service.HomeworkService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class HomeworkController(
        private val homeworkService: HomeworkService
) {
    @GetMapping("")
    fun getAllUsers() = homeworkService.getAllUser()

    @GetMapping("/name/{name}")
    fun getUserByName(@PathVariable("name") name: String): String {
        homeworkService.getUserByName(name)
                .onSuccess { return it }
                .onFailure { return it.message ?: "해당 유저가 없습니다." }
        throw RuntimeException("Unreachable Code")
    }

    @GetMapping("/info")
    fun getUserByInfo(
            @RequestParam("name") name: String,
            @RequestParam("part") part: String
    ): String {
        homeworkService.getUserByInfo(name, part)
                .onSuccess { return it }
                .onFailure { return it.message ?: "해당 유저가 없습니다." }
        throw RuntimeException("Unreachable Code")
    }

    @PostMapping("")
    fun registerUser(@RequestBody userDto: HomeworkUserDto): HomeworkUser = homeworkService.registerUser(userDto)

    @PutMapping("")
    fun putUser(@RequestBody user: HomeworkUser): String {
        homeworkService.putUser(user)
                .onSuccess { return it }
                .onFailure { return it.message ?: "유저 수정 실패" }
        throw RuntimeException("Unreachable Code")
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Int): String {
        homeworkService.deleteUser(id)
                .onSuccess { return it }
                .onFailure { return it.message ?: "유저 삭제 실패" }
        throw RuntimeException("Unreachable Code")
    }
}