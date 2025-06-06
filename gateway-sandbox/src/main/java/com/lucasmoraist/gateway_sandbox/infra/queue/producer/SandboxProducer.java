package com.lucasmoraist.gateway_sandbox.infra.queue.producer;

import com.lucasmoraist.gateway_sandbox.domain.information.EmailData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class SandboxProducer {

    public static final String FROM_GATEWAY_SANDBOX = "fromGatewaySandbox-out-0";
    public static final String JSM_SENDER_ROUTING_KEY = "JsmSenderRoutingKey";

    private final StreamBridge streamBridge;

    public void sendMessage(Message<EmailData> message) {
        log.debug("Sending message to queue: {}", message);
        streamBridge.send(FROM_GATEWAY_SANDBOX, message);
    }

}
