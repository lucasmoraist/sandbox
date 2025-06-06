package com.lucasmoraist.jms_sandbox.service;

import com.lucasmoraist.jms_sandbox.dto.EmailData;

public interface JsmService {
    void sendEmail(EmailData data);
}
