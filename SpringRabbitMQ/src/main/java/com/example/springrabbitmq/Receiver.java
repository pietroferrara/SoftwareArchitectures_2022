package com.example.springrabbitmq;

import org.springframework.stereotype.Component;

@Component
public class Receiver {
    public void receiveMessage(String message) {
        System.out.println("I received "+message);
    }
}
