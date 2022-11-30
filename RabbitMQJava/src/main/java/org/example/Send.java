package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()
                ) {
            channel.queueDeclare("hello", false, false, false, null);
            String message = "Hello world!";
            channel.basicPublish("", "hello", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Message sent!");
        }
    }
}
