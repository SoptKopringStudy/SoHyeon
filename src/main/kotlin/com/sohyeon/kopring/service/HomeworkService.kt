package com.sohyeon.kopring.service

import com.sohyeon.kopring.dto.HomeworkUserDto
import com.sohyeon.kopring.entity.HomeworkUser

interface HomeworkService {

    // String 반환
    fun getAllUser(): String

    // 성공과 실패로 나누어짐
    fun getUserByName(name: String): Result<String>
    fun getUserByInfo(name: String, part: String): Result<String>

    // 객체 반환
    fun registerUser(userDto: HomeworkUserDto): HomeworkUser

    fun putUser(user: HomeworkUser): Result<String>

    fun deleteUser(id: Int): Result<String>

}