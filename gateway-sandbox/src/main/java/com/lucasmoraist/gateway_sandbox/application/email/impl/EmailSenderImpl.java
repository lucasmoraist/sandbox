package com.lucasmoraist.gateway_sandbox.application.email.impl;

import com.lucasmoraist.gateway_sandbox.application.email.EmailSender;
import com.lucasmoraist.gateway_sandbox.domain.information.EmailData;
import com.lucasmoraist.gateway_sandbox.infra.queue.producer.SandboxProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.lucasmoraist.gateway_sandbox.infra.queue.producer.SandboxProducer.JSM_SENDER_ROUTING_KEY;
import static org.springframework.integration.support.MessageBuilder.withPayload;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final SandboxProducer gatewaySandbox;

    @Override
    public void sendEmail(String to, String name) {
        log.debug("Sending email to: {}, name: {}", to, name);
        EmailData emailData = new EmailData(name, to);
        gatewaySandbox.sendMessage(withPayload(emailData)
                .setHeader(JSM_SENDER_ROUTING_KEY, "jms-sandbox")
                .build());
    }

}
