package amqp.rabbitmq.subscribe.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitSnackConfig {

    private static final String EXCHANGE_NAME = "product";
    private static final String ROUTING_KEY = "snack.#";
    private static final String QUEUE_NAME = "snack.queue";

    @Bean(name = "snackExchange")
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean(name = "snackQueue")
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean(name = "snackBinding")
    Binding binding(Queue snackQueue, TopicExchange snackExchange) {
        return BindingBuilder.bind(snackQueue).to(snackExchange).with(ROUTING_KEY);
    }

}
