package com.sohyeon.kopring.controller

import com.sohyeon.kopring.entity.User
import com.sohyeon.kopring.entity.UserResponseDto
import com.sohyeon.kopring.entity.toDto
import com.sohyeon.kopring.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
        val userRepository: UserRepository
) {
    @GetMapping()
    fun getUsers(): ResponseEntity<List<UserResponseDto>> {
        val users = userRepository.findAll().map { it.toDto() }
        return ResponseEntity.ok(users)
    }

    @PostMapping()
    fun setUser(@RequestBody user: User): ResponseEntity<User> {
        val res = userRepository.save(user)
        return ResponseEntity.ok(res)
    }
}