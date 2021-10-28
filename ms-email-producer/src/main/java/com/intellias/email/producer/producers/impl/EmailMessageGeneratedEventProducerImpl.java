package com.intellias.email.producer.producers.impl;

import com.intellias.email.EmailMessageGeneratedEvent;
import com.intellias.email.common.utils.KafkaConstants;
import com.intellias.email.producer.producers.EmailMessageGeneratedEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailMessageGeneratedEventProducerImpl implements EmailMessageGeneratedEventProducer {
    private final KafkaTemplate<String, EmailMessageGeneratedEvent> template;

    @Override
    @SneakyThrows
    public boolean sendEmailMessageGeneratedEvent(
            String id, EmailMessageGeneratedEvent emailMessageGeneratedEvent
    ) {
        template.send(
                KafkaConstants.EMAIL_MESSAGE_GENERATED_TOPIC_AVRO,
                id, emailMessageGeneratedEvent
        ).get();
        return true;
    }
}
