package com.sohyeon.kopring.assignment1

data class Week1UserDto(
        val name: String,
        val part: String
) {
    fun toUser(id: Int) = Week1User(
            id = id.toLong(),
            name = name,
            part = part
    )
}
