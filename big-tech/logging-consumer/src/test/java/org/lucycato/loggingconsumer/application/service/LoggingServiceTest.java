package org.lucycato.loggingconsumer.application.service;

import org.junit.jupiter.api.Test;
import org.lucycato.loggingconsumer.application.port.in.HandleLoggingCommand;
import org.lucycato.loggingconsumer.application.port.in.LoggingUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoggingServiceTest {
    @Autowired
    private LoggingUseCase loggingUseCase;

    @Test
    void handleLogging() {
        HandleLoggingCommand command = new HandleLoggingCommand("exception", "Hello");
        StepVerifier.create(loggingUseCase.handleLogging(command))
                .verifyComplete();
    }
}