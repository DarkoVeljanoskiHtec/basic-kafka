package com.basickafkaproducer;

import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.Future;

@Service
public class ProducerService {

    private final Producer<String,String> producer;

    public ProducerService(ProducerFactory<String,String> producerFactory){
        this.producer = producerFactory.createProducer();
    }

    public void sendMessage(String topic, String message) throws Exception{
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>(topic,message);
        Future<RecordMetadata> future = producer.send(producerRecord);
        try {
            RecordMetadata metadata = future.get();
            System.out.printf("Sent message to topic=%s, partition=%d, offset=%d%n",
                    metadata.topic(), metadata.partition(), metadata.offset());
        } catch (Exception e){
            System.err.println("failed to send message");
            throw e;
        }
    }

    @PreDestroy
    public void closeProducer(){
        producer.close(Duration.ofSeconds(5));
    }
}
