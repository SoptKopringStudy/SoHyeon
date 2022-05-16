package com.sohyeon.kopring.dto

import com.sohyeon.kopring.entity.HomeworkUser

data class HomeworkUserDto(
        val name: String,
        val part: String
) {
    fun toUser(id: Int) = HomeworkUser(
            id = id.toLong(),
            name = name,
            part = part
    )
}
