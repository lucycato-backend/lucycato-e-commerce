package org.lucycato.gatewayserver.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LogRequestCommand extends SelfValidating<LogRequestCommand> {
    @NotEmpty
    private Set<URI> uris;

    @NotBlank
    private String routeServiceId;

    @NotBlank
    private String routeUriPath;

    @NotBlank
    private String method;

    private Map<String, List<String>> headers;

    private String token;


    public LogRequestCommand(Set<URI> uris, String routeServiceId, String routeUriPath, String method, Map<String, List<String>> headers, String token) {
        this.uris = uris;
        this.routeServiceId = routeServiceId;
        this.routeUriPath = routeUriPath;
        this.method = method;
        this.headers = headers;
        this.token = token;

        this.validateSelf();
    }
}
