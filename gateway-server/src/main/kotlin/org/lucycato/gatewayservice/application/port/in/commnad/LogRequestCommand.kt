package org.lucycato.gatewayservice.application.port.`in`.commnad

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.lucycato.common.SelfValidating
import java.net.URI

data class LogRequestCommand(
        @field:NotEmpty
        val uris: Set<URI>,

        @field:NotBlank
        val routeServiceId: String?,

        @field:NotBlank
        val routeUriPath: String?,

        @field:NotBlank
        val method: String?,

        val headers: Map<String, List<String>>,

        val token: String?,
) : SelfValidating<LogRequestCommand>() {
    init {
        validateSelf()
    }
}