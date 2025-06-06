package com.lucasmoraist.jms_sandbox.service;

import com.lucasmoraist.jms_sandbox.dto.EmailData;
import org.springframework.messaging.Message;

public interface OrchestratorMessageGateway {
    void orchestratorMessageFromGatewaySandbox(Message<EmailData> message);
}
