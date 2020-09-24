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
public class RabbitDrinkConfig {

    private static final String EXCHANGE_NAME = "product";
    private static final String ROUTING_KEY = "drink.#";
    private static final String QUEUE_NAME = "drink.queue";

    @Bean(name = "drinkExchange")
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean(name = "drinkQueue")
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean(name = "drinkBinding")
    Binding binding(Queue drinkQueue, TopicExchange drinkExchange) {
        return BindingBuilder.bind(drinkQueue).to(drinkExchange).with(ROUTING_KEY);
    }

}
