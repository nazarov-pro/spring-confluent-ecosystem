package com.intellias.email.producer.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.intellias.email.EmailMessage;
import com.intellias.email.EmailMessageGeneratedEvent;
import com.intellias.email.common.dto.SendEmailMessageRequest;
import com.intellias.email.common.dto.SendEmailMessageResponse;
import com.intellias.email.producer.producers.EmailMessageGeneratedEventProducer;
import com.intellias.email.producer.services.impl.EmailServiceImpl;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {
    @Mock
    private EmailMessageGeneratedEventProducer emailMessageGeneratedEventProducer;
    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    void givenSendEmailRequestWhenSendThenProduceEvent() {
        final var sendEmailMessageRequest = SendEmailMessageRequest.builder()
                .subject("Test Subject")
                .to(List.of("me@shahinnazarov.com"))
                .from("no-reply@shahinnazarov.com")
                .content("Test Content")
                .build();
        when(emailMessageGeneratedEventProducer.sendEmailMessageGeneratedEvent(any(), any())).thenReturn(true);

        final var response = emailService.send(sendEmailMessageRequest);

        verify(emailMessageGeneratedEventProducer, times(1)).sendEmailMessageGeneratedEvent(any(), any());
        Assertions.assertNotNull(response);
    }

}
