package com.intellias.email.avro;

import com.intellias.email.EmailMessage;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailMessageAvroTest {

    @Test
    void givenNullContentWhenSerializeThenThrowException() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> AvroUtils.serializeToJson(
                        EmailMessage.newBuilder(EMAIL_MESSAGE).clearContent().build(),
                        EmailMessage.class)
        );
    }

    @Test
    void givenEmailMessageWhenSerializeThenSerializeToJsonBytes() {
        final var jsonBytes = AvroUtils.serializeToJson(EMAIL_MESSAGE, EmailMessage.class);
        Assertions.assertNotNull(jsonBytes);
        Assertions.assertTrue(jsonBytes.length > 0);
    }

    @Test
    void givenEmailMessageWhenSerializeThenSerializeToBinaryBytes() {
        final var binary = AvroUtils.serializeToBinary(EMAIL_MESSAGE, EmailMessage.class);
        Assertions.assertNotNull(binary);
        Assertions.assertTrue(binary.length > 0);
    }

    @Test
    void givenEmailMessageWhenSerializeAndDeserializeJsonThenReturnTheSameElement() {
        final var jsonBytes = AvroUtils.serializeToJson(EMAIL_MESSAGE, EmailMessage.class);
        final var deserializedEmailMessage = AvroUtils.deserializeFromJson(
                EmailMessage.getClassSchema(), jsonBytes, EmailMessage.class
        );

        Assertions.assertNotNull(deserializedEmailMessage);
        Assertions.assertEquals(EMAIL_MESSAGE, deserializedEmailMessage);
    }

    @Test
    void givenEmailMessageWhenSerializeAndDeserializeBinaryThenReturnTheSameElement() {
        final var jsonBytes = AvroUtils.serializeToBinary(EMAIL_MESSAGE, EmailMessage.class);
        final var deserializedEmailMessage = AvroUtils.deserializeFromBinary(
                EmailMessage.getClassSchema(), jsonBytes, EmailMessage.class
        );

        Assertions.assertNotNull(deserializedEmailMessage);
        Assertions.assertEquals(EMAIL_MESSAGE, deserializedEmailMessage);
    }

    private static final EmailMessage EMAIL_MESSAGE = EmailMessage.newBuilder()
            .setFrom("no-reply@shahinnazarov.com")
            .setTo(List.of("me@shahinnazarov.com"))
            .setContent("Demo").build();
}
