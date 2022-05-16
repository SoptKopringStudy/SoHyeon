package com.sohyeon.kopring.service

import com.sohyeon.kopring.dto.HomeworkUserDto
import com.sohyeon.kopring.entity.HomeworkUser
import org.springframework.stereotype.Service

@Service
class HomeworkServiceImpl: HomeworkService {
    private val userList: MutableList<HomeworkUser> = mutableListOf()

    // 모든 유저 정보 가져오기
    override fun getAllUser(): String =
            if(userList.isEmpty()) "유저가 없습니다."
            else userList.joinToString(", ")

    // 파라미터(이름)로 유저 정보 가져오기
    override fun getUserByName(name: String): Result<String> {
        return when(val user = userList.find { it.isNameSame(name) }) {
            null -> Result.failure(IllegalStateException("${name}을 가진 유저가 없습니다."))
            else -> Result.success(user.introduce())
        }
    }

    // 쿼리(이름, 파트)로 유저 정보 가져오기
    override fun getUserByInfo(name: String, part: String): Result<String> {
        return when(val user = userList.find { it.isUserInfo(name, part) }) {
            null -> Result.failure(IllegalStateException("유저 조회 실패"))
            else -> Result.success("유저 조회 성공")
        }
    }

    // 유저 정보 삽입
    override fun registerUser(userDto: HomeworkUserDto): HomeworkUser {
        val user = userDto.toUser(userList.size)
        userList.add(user) // 유저 추가

        return user
    }

    // 유저 정보 수정
    override fun putUser(user: HomeworkUser): Result<String> {
        return when(val user = userList.find { it.isIdEqual(user.id) }) {
            null -> Result.failure(IllegalStateException("유저 수정 실패"))
            else -> Result.success(("유저 수정 성공"))
        }
    }

    // 유저 정보 삭제
    override fun deleteUser(id: Int): Result<String> {
        return when(val user = userList.find { it.isIdEqual(id.toLong()) }) {
            null -> Result.failure(IllegalStateException("유저 삭제 실패"))
            else -> {
                userList.removeIf { it.isIdEqual(id.toLong()) }
                Result.success("유저 삭제 성공")
            }
        }
    }
}