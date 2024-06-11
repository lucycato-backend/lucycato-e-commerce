package org.lucycato.gatewayservice.application.port.`in`.commnad

import jakarta.validation.constraints.NotEmpty
import org.lucycato.common.SelfValidating

data class LogResponseCommand(
        val statusCode: Int,

        @field:NotEmpty
        val headers: Map<String, List<String>>
): SelfValidating<LogResponseCommand>() {
    init {
        validateSelf()
    }
}