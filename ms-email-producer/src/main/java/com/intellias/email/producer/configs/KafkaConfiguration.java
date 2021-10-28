package com.intellias.email.producer.configs;

import static com.intellias.email.common.utils.KafkaConstants.DEFAULT_REPLICAS;
import static com.intellias.email.common.utils.KafkaConstants.EMAIL_MESSAGE_GENERATED_TOPIC_AVRO;
import static com.intellias.email.common.utils.KafkaConstants.EMAIL_MESSAGE_GENERATED_TOPIC_AVRO_PARTITIONS;
import static com.intellias.email.common.utils.KafkaConstants.EMAIL_MESSAGE_GENERATED_TOPIC_JSON;
import static com.intellias.email.common.utils.KafkaConstants.EMAIL_MESSAGE_GENERATED_TOPIC_JSON_PARTITIONS;
import static com.intellias.email.common.utils.KafkaConstants.EMAIL_MESSAGE_GENERATED_TOPIC_PROTO;
import static com.intellias.email.common.utils.KafkaConstants.EMAIL_MESSAGE_GENERATED_TOPIC_PROTO_PARTITIONS;

import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
//@EnableBinding(Processor.class)
public class KafkaConfiguration {

    @Bean
    public NewTopic emailMessageGeneratedAvroTopic() {
        return TopicBuilder.name(EMAIL_MESSAGE_GENERATED_TOPIC_AVRO)
                .partitions(EMAIL_MESSAGE_GENERATED_TOPIC_AVRO_PARTITIONS)
                .replicas(DEFAULT_REPLICAS)
                .build();
    }

    @Bean
    public NewTopic emailMessageGeneratedJsonTopic() {
        return TopicBuilder.name(EMAIL_MESSAGE_GENERATED_TOPIC_JSON)
                .partitions(EMAIL_MESSAGE_GENERATED_TOPIC_JSON_PARTITIONS)
                .replicas(DEFAULT_REPLICAS)
                .build();
    }

    @Bean
    public NewTopic emailMessageGeneratedProtoTopic() {
        return TopicBuilder.name(EMAIL_MESSAGE_GENERATED_TOPIC_PROTO)
                .partitions(EMAIL_MESSAGE_GENERATED_TOPIC_PROTO_PARTITIONS)
                .replicas(DEFAULT_REPLICAS)
                .build();
    }
}
