package com.sohyeon.kopring.entity

import org.apache.tomcat.jni.Local
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long,
        @Column(nullable = false) val account: String = "",
        val email: String = "",
        private val phoneNumber: String = "",
        @Column(nullable = false)
        private val createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(nullable = false)
        private val createdBy: String = "",
        private val updatedAt: LocalDateTime = LocalDateTime.now(),
        private val uodatedBy: String = "",
) {
        constructor() : this(0)
}
