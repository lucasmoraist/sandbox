package com.lucasmoraist.jms_sandbox.service.impl;

import com.lucasmoraist.jms_sandbox.dto.EmailData;
import com.lucasmoraist.jms_sandbox.service.JsmService;
import com.lucasmoraist.jms_sandbox.service.OrchestratorMessageGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrchestratorMessageGatewayImpl implements OrchestratorMessageGateway {

    private final JsmService jsmService;

    @Override
    public void orchestratorMessageFromGatewaySandbox(Message<EmailData> message) {
        log.info("Received message from gateway sandbox");
        EmailData data = message.getPayload();
        log.debug("Message payload: {}", data);
        this.jsmService.sendEmail(data);
        log.info("Email sent successfully");
    }

}
