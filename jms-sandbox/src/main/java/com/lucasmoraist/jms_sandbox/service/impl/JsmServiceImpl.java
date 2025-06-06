package com.lucasmoraist.jms_sandbox.service.impl;

import com.lucasmoraist.jms_sandbox.dto.EmailData;
import com.lucasmoraist.jms_sandbox.service.JsmService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Log4j2
@Service
@RequiredArgsConstructor
public class JsmServiceImpl implements JsmService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailData data) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setTo(data.to());
            helper.setSubject(String.format("Hello %s! Confirm your email.", data.name()));
            helper.setText(this.buildMessage(), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error creating email message", e);
            throw new RuntimeException("Failed to create email message", e);
        }
    }

    private String buildMessage() {
        return """
                <html>
                    <body>
                        <h1>Confirm your email</h1>
                        <p>Click the link below to confirm your email address:</p>
                        <a href="https://example.com/confirm?email=%s">Confirm Email</a>
                    </body>
                </html>
                """;
    }

}
