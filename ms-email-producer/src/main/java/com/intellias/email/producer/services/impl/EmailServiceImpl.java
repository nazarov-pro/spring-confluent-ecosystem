package com.intellias.email.producer.services.impl;

import com.intellias.email.EmailMessage;
import com.intellias.email.EmailMessageGeneratedEvent;
import com.intellias.email.common.dto.SendEmailMessageRequest;
import com.intellias.email.common.dto.SendEmailMessageResponse;
import com.intellias.email.producer.services.EmailService;
import com.intellias.email.producer.producers.EmailMessageGeneratedEventProducer;
import java.time.Clock;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final EmailMessageGeneratedEventProducer emailMessageGeneratedEventProducer;

    @Override
    @SneakyThrows
    public SendEmailMessageResponse send(SendEmailMessageRequest request) {
        log.info("Email send request received to: {}", request.getTo());
        String id = UUID.randomUUID().toString();
        final var now = Instant.now(Clock.systemUTC());
        emailMessageGeneratedEventProducer.sendEmailMessageGeneratedEvent(
                id,
                EmailMessageGeneratedEvent.newBuilder()
                        .setId(id)
                        .setTimestamp(now)
                        .setIssuer("me")
                        .setBody(
                                EmailMessage.newBuilder()
                                        .setFrom(request.getFrom())
                                        .setTo(request.getTo())
                                        .setCc(request.getCc())
                                        .setBcc(request.getBcc())
                                        .setAttachments(request.getAttachments())
                                        .setMimeMessage(request.isMimeMessage())
                                        .setContent(request.getContent())
                                        .build()
                        )
                        .build()
        );
        return SendEmailMessageResponse.builder().id(id).timestamp(now.toEpochMilli()).build();
    }
}
