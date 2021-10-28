package com.intellias.email.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class KafkaConstants {
    public static final int DEFAULT_REPLICAS = 1;
    public static final String EMAIL_MESSAGE_GENERATED_TOPIC_AVRO = "intellias.email-msg-generated.avro";
    public static final int EMAIL_MESSAGE_GENERATED_TOPIC_AVRO_PARTITIONS = 12;
    public static final String EMAIL_MESSAGE_GENERATED_TOPIC_JSON = "intellias.email-msg-generated.json";
    public static final int EMAIL_MESSAGE_GENERATED_TOPIC_JSON_PARTITIONS = 12;
    public static final String EMAIL_MESSAGE_GENERATED_TOPIC_PROTO = "intellias.email-msg-generated.proto";
    public static final int EMAIL_MESSAGE_GENERATED_TOPIC_PROTO_PARTITIONS = 12;

    public static final String KAFKA_TEMPLATE_STRING_AVRO = "kafka-template-string-avro";

}
