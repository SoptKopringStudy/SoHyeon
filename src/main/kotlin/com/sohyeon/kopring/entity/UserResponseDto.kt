package com.sohyeon.kopring.entity

data class UserResponseDto(
        val account: String,
        val email: String
)

fun User.toDto() = UserResponseDto(account, email)
