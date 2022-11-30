package com.example.springrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sender implements CommandLineRunner {
    RabbitTemplate template;
    Receiver receiver;

    public Sender(RabbitTemplate template, Receiver receiver) {
        this.template = template;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending a message");
        template.convertAndSend("logs", "foo.bar.baz", "Hello");
    }
}
