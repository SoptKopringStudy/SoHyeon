package com.sohyeon.kopring.controller

import com.sohyeon.kopring.entity.HomeworkUser
import com.sohyeon.kopring.dto.HomeworkUserDto
import com.sohyeon.kopring.entity.BaseResponse
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
    fun getUserByName(@PathVariable("name") name: String): BaseResponse<String> {
        homeworkService.getUserByName(name)
                .onSuccess { return BaseResponse.success<String>(it) }
                .onFailure { return BaseResponse.failure(it.message ?: "해당 유저가 없습니다.") }
        throw RuntimeException("Unreachable Code")
    }

    /**
     * {
     *      status: 200
     *      success: true
     *      data : {
     *                 "김소현"
     *             }
     */

    @GetMapping("/info")
    fun getUserByInfo(
            @RequestParam("name") name: String,
            @RequestParam("part") part: String
    ): BaseResponse<String> {
        homeworkService.getUserByInfo(name, part)
                .onSuccess { return BaseResponse.success<String>(it) }
                .onFailure { return BaseResponse.failure(it.message ?: "해당 유저가 없습니다.") }
        throw RuntimeException("Unreachable Code")
    }

    @PostMapping("")
    fun registerUser(@RequestBody userDto: HomeworkUserDto): HomeworkUser = homeworkService.registerUser(userDto)

    @PutMapping("")
    fun putUser(@RequestBody user: HomeworkUser): BaseResponse<String> {
        homeworkService.putUser(user)
                .onSuccess { return BaseResponse.success<String>(it) }
                .onFailure { return BaseResponse.failure(it.message ?: "수정 실패") }
        throw RuntimeException("Unreachable Code")
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Int): BaseResponse<String> {
        homeworkService.deleteUser(id)
                .onSuccess { return BaseResponse.success<String>(it) }
                .onFailure { return BaseResponse.failure(it.message ?: "삭제 실패") }
        throw RuntimeException("Unreachable Code")
    }
}