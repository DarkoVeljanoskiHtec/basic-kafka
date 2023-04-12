package com.basickafkaconsumer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    private ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService){
        this.consumerService = consumerService;
    }

    @PostMapping("/consume")
    public void consumeMessage(@RequestBody String message){
        consumerService.consume(message);
    }
}
