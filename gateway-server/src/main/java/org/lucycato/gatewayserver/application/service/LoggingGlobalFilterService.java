package org.lucycato.gatewayserver.application.service;

import lombok.extern.slf4j.Slf4j;
import org.lucycato.gatewayserver.application.port.in.LoggingGlobalFilterUseCase;
import org.lucycato.gatewayserver.application.port.in.command.LogRequestCommand;
import org.lucycato.gatewayserver.application.port.in.command.LogResponseCommand;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class LoggingGlobalFilterService implements LoggingGlobalFilterUseCase {

    @Override
    public Mono<Void> logRequest(LogRequestCommand command) {
        return Mono.fromRunnable(() -> {
            String originalUri = (command.getUris().isEmpty()) ? "Unknown" : command.getUris().iterator().next().toString();
            String routeUri = "lb://" + command.getRouteServiceId() + command.getRouteUriPath();

            StringBuilder headers = new StringBuilder();
            command.getHeaders().forEach((headerName, headerValues) -> {
                headers
                        .append("   - ")
                        .append(headerName)
                        .append(": ")
                        .append(String.join(", ", headerValues))
                        .append("\n");
            });

            log.info("\n>> -- >> -- >> -- >> REQUEST  >> -- >> -- >> -- >>\n"
                            + "Original URL: {}\n"
                            + "Route URL: {}\n"
                            + "Jwt: {}\n"
                            + "Method: {}\n"
                            + "Headers:\n{}\n"
                            + ">> -- >> -- >> -- >> REQUEST  >> -- >> -- >> -- >>\n",
                    originalUri,
                    routeUri,
                    command.getToken(),
                    command.getMethod(),
                    headers
            );
        });
    }

    @Override
    public Mono<Void> logResponse(LogResponseCommand command) {
        return Mono.fromRunnable(() -> {
            StringBuilder headers = new StringBuilder();
            command.getHeaders().forEach((headerName, headerValues) -> {
                headers
                        .append("   - ")
                        .append(headerName)
                        .append(": ")
                        .append(String.join(", ", headerValues))
                        .append("\n");
            });

            log.info("\n<< -- << -- << -- << RESPONSE << -- << -- << -- <<\n"
                            + "Status: {}\n"
                            + "Headers:\n{}\n"
                            + "<< -- << -- << -- << RESPONSE << -- << -- << -- <<\n",
                    command.getStatusCode(),
                    headers
            );
        });
    }
}
