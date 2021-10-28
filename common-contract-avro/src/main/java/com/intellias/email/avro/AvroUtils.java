package com.intellias.email.avro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;

@UtilityClass
@Slf4j
public class AvroUtils {
    private enum MessageFormatTypes {JSON, BINARY}

    public static <T extends SpecificRecordBase> byte[] serializeToJson(
            final T message, final Class<T> clazz) {
        return serialize(MessageFormatTypes.JSON, message, clazz);
    }

    public static <T extends SpecificRecordBase> byte[] serializeToBinary(
            final T message, final Class<T> clazz) {
        return serialize(MessageFormatTypes.BINARY, message, clazz);
    }

    public static <T extends SpecificRecordBase> T deserializeFromJson(
            final Schema schema, final byte[] bytes, final Class<T> clazz) {
        return deserialize(MessageFormatTypes.JSON, schema, bytes, clazz);
    }

    public static <T extends SpecificRecordBase> T deserializeFromBinary(
            final Schema schema, final byte[] bytes, final Class<T> clazz) {
        return deserialize(MessageFormatTypes.BINARY, schema, bytes, clazz);
    }

    private static <T extends SpecificRecordBase> byte[] serialize(
            final MessageFormatTypes encoderType,
            final T message,
            final Class<T> clazz
    ) {
        final var writer = new SpecificDatumWriter<>(clazz);
        final var stream = new ByteArrayOutputStream();
        try {
            Encoder encoder;
            switch (encoderType) {
                case JSON:
                    encoder = EncoderFactory.get().jsonEncoder(message.getSchema(), stream);
                    break;
                case BINARY:
                    encoder = EncoderFactory.get().binaryEncoder(stream, null);
                    break;
                default:
                    log.error("{} is not registered encoder type.", encoderType);
                    throw new IllegalArgumentException(encoderType + " is not registered.");
            }

            writer.write(message, encoder);
            encoder.flush();
            return stream.toByteArray();
        } catch (IOException e) {
            log.error("Serialization error:" + e.getMessage());
            throw new RuntimeException("Serialization was failed", e);
        }
    }


    private static <T extends SpecificRecordBase> T deserialize(
            final MessageFormatTypes decoderType,
            final Schema schema,
            final byte[] data,
            final Class<T> clazz
    ) {
        final var reader = new SpecificDatumReader<>(clazz);
        try {
            Decoder decoder;
            switch (decoderType) {
                case JSON:
                    decoder = DecoderFactory.get().jsonDecoder(schema, new String(data));
                    break;
                case BINARY:
                    decoder = DecoderFactory.get().binaryDecoder(data, null);
                    break;
                default:
                    log.error("{} is not registered decoder type.", decoderType);
                    throw new IllegalArgumentException(decoderType + " is not registered.");
            }
            return reader.read(null, decoder);
        } catch (IOException e) {
            log.error("Deserialization error:" + e.getMessage());
            throw new RuntimeException("Deserialization was failed", e);
        }
    }


}
