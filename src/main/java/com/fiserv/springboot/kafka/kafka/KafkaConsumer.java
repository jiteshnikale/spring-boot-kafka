package com.fiserv.springboot.kafka.kafka;

import com.fiserv.springboot.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        LOG.info(String.format("Message received %s", message));
    }

    @KafkaListener(topics = "${spring.kafka.json.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeJson(User message) {
        LOG.info(String.format("Message received %s", message.toString()));
    }
}
