package com.sohyeon.kopring.entity

import org.springframework.http.HttpStatus

data class BaseResponse<T>(
        val statusCode: Int,
        val success: Boolean,
        val data: T,
        val message: String
) {
    // static 비슷한 거..!
    companion object {
        fun <T> success(data: T) = BaseResponse<T> (
                statusCode = HttpStatus.OK.value(),
                success = true,
                data = data,
                message = "SUCCESS"
                )

        fun failure(message: String) = BaseResponse<String> (
                statusCode = HttpStatus.NOT_FOUND.value(),
                success = false,
                data = message,
                message = "FAILURE"
                )
    }
}
