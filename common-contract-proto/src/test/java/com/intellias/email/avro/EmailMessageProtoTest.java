package com.intellias.email.avro;

import com.google.protobuf.InvalidProtocolBufferException;
import com.intellias.proto.EmailMessage;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailMessageProtoTest {

    @Test
    void givenEmailMessageWhenSerializeThenSerializeToBinary() {
        final var bytes = emailMessage.toByteArray();
        Assertions.assertNotNull(bytes);
        Assertions.assertTrue(bytes.length > 0);
    }

    @Test
    void givenEmailMessageWhenSerializeAndDeserializeThenReturnTheSame() throws InvalidProtocolBufferException {
        final var start = System.currentTimeMillis();
        final var bytes = emailMessage.toByteArray();
        final var serialized = System.currentTimeMillis();
        final var generated = EmailMessage.parseFrom(bytes);
        final var deserialized = System.currentTimeMillis();
        System.out.printf(
                "Proto buff message generated bytes: %d, serialized %d ms, deserialized: %d ms\n",
                bytes.length, serialized - start, deserialized - serialized
        );

        Assertions.assertEquals(emailMessage, generated);
    }

    private final EmailMessage emailMessage = EmailMessage.newBuilder()
            .setFrom("no-reply@shahinnazarov.com")
            .addAllTo(List.of("me@shahinnazarov.com"))
            .setContent("Demo").build();
}
