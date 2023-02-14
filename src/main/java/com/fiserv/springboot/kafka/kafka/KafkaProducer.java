package com.fiserv.springboot.kafka.kafka;

import com.fiserv.springboot.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaTemplate<String, User> kafkaJsonTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topicBankResponse;

    @Value("${spring.kafka.json.topic.name}")
    private String topicJsonBankResponse;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, User> kafkaJsonTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaJsonTemplate = kafkaJsonTemplate;
    }

    public void sendMessage(String message) {
        LOG.info(String.format("Message sent %s", message));
        kafkaTemplate.send(topicBankResponse, message);
    }

    public void sendJsonMessage(User user) {
        LOG.info(String.format("Message sent %s", user.toString()));
        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, topicJsonBankResponse)
                .build();

        kafkaJsonTemplate.send(message);
    }
}
