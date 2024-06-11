package org.lucycato.gatewayserver.application.port.in.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LogResponseCommand extends SelfValidating<LogResponseCommand> {
    @NotNull
    private Integer statusCode;

    @NotEmpty
    private Map<String, List<String>> headers;

    public LogResponseCommand(Integer statusCode, Map<String, List<String>> headers) {
        this.statusCode = statusCode;
        this.headers = headers;

        this.validateSelf();
    }
}
