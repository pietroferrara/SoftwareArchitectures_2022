package it.unive.dais.sa.dronesystem;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DroneSystemApplication {

    static final String topicExchangeName = "start";

    static final String queueName = "droneDeliveries";

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public Binding binding(Queue q, TopicExchange e) {
        return BindingBuilder.bind(q).to(e).with("foo.bar.#");
    }


    @Bean
    SimpleMessageListenerContainer container(
            ConnectionFactory cf, MessageListenerAdapter la) {
        SimpleMessageListenerContainer c = new SimpleMessageListenerContainer();
        c.setConnectionFactory(cf);
        c.setQueueNames(queueName);
        c.setMessageListener(la);
        return c;
    }
    @Bean
    MessageListenerAdapter listenerAdapter(DeliveryManager rec) {
        return new MessageListenerAdapter(rec, "receiveRequest");
    }



    public static void main(String[] args) {
        SpringApplication.run(DroneSystemApplication.class, args);
    }

}
