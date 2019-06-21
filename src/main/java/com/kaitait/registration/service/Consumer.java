package com.kaitait.registration.service;

import com.kaitait.registration.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public User consume(User user) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", user));
        return user;
    }

//    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
//    public User consume(final User user) throws IOException {
//        log.warn(String.format("#### -> Consumed user: -> %s", user));
//        return user;
//    }
}
