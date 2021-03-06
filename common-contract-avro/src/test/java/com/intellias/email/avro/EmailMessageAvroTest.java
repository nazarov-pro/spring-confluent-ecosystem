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
        final var start = System.currentTimeMillis();
        final var jsonBytes = AvroUtils.serializeToJson(EMAIL_MESSAGE, EmailMessage.class);
        final var serialized = System.currentTimeMillis();
        final var deserializedEmailMessage = AvroUtils.deserializeFromJson(
                EmailMessage.getClassSchema(), jsonBytes, EmailMessage.class
        );
        final var deserialized = System.currentTimeMillis();
        System.out.printf(
                "JSON message generated bytes: %d, serialized %d ms, deserialized: %d ms\n",
                jsonBytes.length, serialized - start, deserialized - serialized
        );
        System.out.println(new String(jsonBytes));
        Assertions.assertNotNull(deserializedEmailMessage);
        Assertions.assertEquals(EMAIL_MESSAGE, deserializedEmailMessage);
    }

    @Test
    void givenEmailMessageWhenSerializeAndDeserializeBinaryThenReturnTheSameElement() {
        final var start = System.currentTimeMillis();
        final var binary = AvroUtils.serializeToBinary(EMAIL_MESSAGE, EmailMessage.class);
        final var serialized = System.currentTimeMillis();
        final var deserializedEmailMessage = AvroUtils.deserializeFromBinary(
                EmailMessage.getClassSchema(), binary, EmailMessage.class
        );
        final var deserialized = System.currentTimeMillis();

        System.out.printf(
                "Avro message generated bytes: %d, serialized %d ms, deserialized: %d ms\n",
                binary.length, serialized - start, deserialized - serialized
        );

        Assertions.assertNotNull(deserializedEmailMessage);
        Assertions.assertEquals(EMAIL_MESSAGE, deserializedEmailMessage);
    }

    private static final EmailMessage EMAIL_MESSAGE = EmailMessage.newBuilder()
            .setFrom("no-reply@shahinnazarov.com")
            .setTo(List.of("me@shahinnazarov.com"))
            .setContent("Demo").build();
}
