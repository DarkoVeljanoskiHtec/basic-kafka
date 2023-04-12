package com.basickafkaproducer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService){
        this.producerService = producerService;
    }

    @PostMapping("/{topic}")
    public ResponseEntity<String> sendMessage(@PathVariable String topic, @RequestBody String message) throws Exception {
        producerService.sendMessage(topic,message);
        return ResponseEntity.ok("Message sent to kafka topic: " + topic);
    }
}
