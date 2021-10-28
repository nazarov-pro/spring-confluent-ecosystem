package com.intellias.email.producer.controllers;

import com.intellias.email.common.dto.ApiMessageResponse;
import com.intellias.email.common.dto.SendEmailMessageRequest;
import com.intellias.email.common.dto.SendEmailMessageResponse;
import com.intellias.email.producer.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/email-messages")
public class EmailMessageController {
    private final EmailService emailService;

    @PostMapping
    public ApiMessageResponse<SendEmailMessageResponse> send(
            @RequestBody final SendEmailMessageRequest request
    ) {
        return ApiMessageResponse.success(emailService.send(request));
    }
}
