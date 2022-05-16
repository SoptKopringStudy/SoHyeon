package com.sohyeon.kopring.controller

import com.sohyeon.kopring.entity.HomeworkUser
import com.sohyeon.kopring.dto.HomeworkUserDto
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
class HomeworkController {
    private val userList: MutableList<HomeworkUser> = mutableListOf()

    @GetMapping("")
    fun getAllUsers() =
            if(userList.isEmpty()) "유저가 없습니다."
            else userList.joinToString(", ")

    @GetMapping("/name/{name}")
    fun getUserByName(@PathVariable("name") name: String) =
            when (val user = userList.find { it.name == name }) {
                null -> "${name}을 가진 유저가 없습니다."
                else -> user.introduce()
            }

    @GetMapping("/info")
    fun getUserByInfo(
            @RequestParam("part") part: String,
            @RequestParam("name") name: String
    ): String {
        return when (userList.find { it.name==name && it.part==part }) {
            null ->"조회 실패"
            else -> "조회 성공"
        }
    }

    @PostMapping("")
    fun registerUser(@RequestBody userDto: HomeworkUserDto): HomeworkUser {
        val user = userDto.toUser(userList.size)
        userList.add(user)
        return user
    }

    @PutMapping("")
    fun putUser(@RequestBody week1User: HomeworkUser) {
        val putIndex = userList.indexOfFirst { it.id == week1User.id }
        if(putIndex != -1) userList[putIndex] = week1User
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Int): String {
        return when(val index = userList.find { it.id == id.toLong() }) {
            null -> "해당 유저는 없습니다."
            else -> {
                userList.removeIf { it.id == id.toLong() }
                "유저를 삭제했습니다."
            }

        }
    }
}