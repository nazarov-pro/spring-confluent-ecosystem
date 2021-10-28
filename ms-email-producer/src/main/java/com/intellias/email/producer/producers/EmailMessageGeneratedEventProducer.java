package com.intellias.email.producer.producers;

import com.intellias.email.EmailMessageGeneratedEvent;

public interface EmailMessageGeneratedEventProducer {

    boolean sendEmailMessageGeneratedEvent(
            String id,
            EmailMessageGeneratedEvent emailMessageGeneratedEvent
    );
}
