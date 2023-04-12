package com.basickafkaconsumer;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "my-topic")
    public void consume(String message){
        System.out.println("Received message: " + message);
    }
}
