package com.sohyeon.kopring.repository

import com.sohyeon.kopring.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>