package com.fiserv.springboot.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String topicBankResponse;

    @Value("${spring.kafka.json.topic.name}")
    private String topicJsonBankResponse;

    @Bean
    public NewTopic bankResponseTopic() {
        return TopicBuilder.name(topicBankResponse)
                .build();

    }
    @Bean
    public NewTopic jsonBankResponseTopic() {
        return TopicBuilder.name(topicJsonBankResponse)
                .build();

    }
}
