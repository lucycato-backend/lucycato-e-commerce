package org.lucycato.gatewayservice.error

import org.lucycato.common.error.ErrorCode
import org.springframework.http.HttpStatus


enum class GatewayErrorCodeImpl(
        private val httpCode: Int,
        private val code: String,
        private val reason: String,
        private val frontMessage: String
) : ErrorCode {
    INVALID_URI(HttpStatus.BAD_REQUEST.value(), "LG-0001", "invalid uri", ""),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "LG-0002", "invalid token", "")
    ;
    override fun getHttpCode(): Int = httpCode
    override fun getCode(): String = code
    override fun getReason(): String = reason
    override fun getFrontMessage(): String = frontMessage
}