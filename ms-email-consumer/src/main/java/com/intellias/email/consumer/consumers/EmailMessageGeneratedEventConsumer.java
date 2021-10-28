package com.intellias.email.consumer.consumers;

import com.intellias.email.EmailMessageGeneratedEvent;
import com.intellias.email.common.utils.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailMessageGeneratedEventConsumer {

    @KafkaListener(
            topics = KafkaConstants.EMAIL_MESSAGE_GENERATED_TOPIC_AVRO
    )
    public void consume(
            @Payload EmailMessageGeneratedEvent event,
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key
    ) {
        log.info("Email message generated event received. event: {}, key: {}", event, key);
    }
}
