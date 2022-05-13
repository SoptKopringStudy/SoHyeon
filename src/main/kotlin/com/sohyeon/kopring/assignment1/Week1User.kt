package com.sohyeon.kopring.assignment1

data class Week1User(
        val id: Long,
        val name: String,
        val part: String
) {
    fun introduce() = "이름: ${name}, 파트: ${part}"
}
