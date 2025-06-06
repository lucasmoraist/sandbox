package com.lucasmoraist.jms_sandbox.queue;

import com.lucasmoraist.jms_sandbox.dto.EmailData;
import com.lucasmoraist.jms_sandbox.service.OrchestratorMessageGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class SandboxConsumer {

    private final OrchestratorMessageGateway orchestratorMessageGateway;

    @Bean
    public Consumer<Message<EmailData>> fromGatewaySandbox() {
        try {
            return orchestratorMessageGateway::orchestratorMessageFromGatewaySandbox;
        } catch (Exception e) {
            log.error("Error creating consumer for orchestrator messages from gateway sandbox", e);
            throw new RuntimeException("Failed to create consumer for orchestrator messages from gateway sandbox", e);
        }
    }

}
