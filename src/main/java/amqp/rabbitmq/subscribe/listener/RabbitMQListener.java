package amqp.rabbitmq.subscribe.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQListener {

    @RabbitListener(queues = "snack.queue")
    public void receiveSnackMessage(final Message message) {
        log.info("receiveSnackMessage : {}", message);
        MessageProperties messageProperties = message.getMessageProperties();
        String routingKey = messageProperties.getReceivedRoutingKey();
        String body = new String(message.getBody());
        log.info("{} : {}", routingKey, body);
    }

    @RabbitListener(queues = "drink.queue")
    public void receiveDrinkMessage(final Message message) {
        log.info("receiveDrinkMessage : {}", message);
        MessageProperties messageProperties = message.getMessageProperties();
        String routingKey = messageProperties.getReceivedRoutingKey();
        String body = new String(message.getBody());
        log.info("{} : {}", routingKey, body);
    }

}
