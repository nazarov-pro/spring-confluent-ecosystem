package com.intellias.email.producer.services;

import com.intellias.email.common.dto.SendEmailMessageRequest;
import com.intellias.email.common.dto.SendEmailMessageResponse;

public interface EmailService {
    SendEmailMessageResponse send(SendEmailMessageRequest request);
}
