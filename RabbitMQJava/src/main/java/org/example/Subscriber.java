package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Subscriber {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("logs", "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, "logs", "");

            System.out.println("Waiting for messages");

            DeliverCallback callback = (consumerTag, message) -> {
                String msg = new String(message.getBody(), StandardCharsets.UTF_8);
                System.out.println(msg);
            };
            channel.basicConsume(queueName, true, callback, consumerTag -> {});
    }
}
