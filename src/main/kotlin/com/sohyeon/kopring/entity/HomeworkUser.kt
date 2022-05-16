package com.sohyeon.kopring.entity

data class HomeworkUser(
        val id: Long,
        val name: String,
        val part: String
) {
    fun introduce() = "이름: ${name}, 파트: ${part}"
    fun isNameSame(other: String) = other==name
    fun isUserInfo(otherName: String, otherPart: String) = otherName==name && otherPart==part
    fun isIdEqual(id: Long) = id==this.id
}
