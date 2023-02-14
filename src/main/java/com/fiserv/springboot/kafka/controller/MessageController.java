package com.fiserv.springboot.kafka.controller;

import com.fiserv.springboot.kafka.kafka.KafkaProducer;
import com.fiserv.springboot.kafka.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);

        return new ResponseEntity<>("Message sent to the topic", HttpStatus.OK);
    }

    @PostMapping("/sendUser")
    public ResponseEntity<String> sendUserData(@RequestBody User user) {
        kafkaProducer.sendJsonMessage(user);

        return new ResponseEntity<>("JSON Message sent to the topic", HttpStatus.OK);
    }
}
