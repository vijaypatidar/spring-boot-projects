package com.example.producer.rest;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.producer.kafka.KafkaTopics.BUY_TOPIC;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TopicController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send(BUY_TOPIC, msg);
    }

    @PostMapping()
    public void send() {
        sendMessage(System.currentTimeMillis() + "");
    }
}
