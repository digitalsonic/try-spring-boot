package microbucks.baristaservice.message;

import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BaristaListener {
    public static final int READY_STATE = 3;
    @Autowired
    private AmqpTemplate orderAmqpTemplate;

    @RabbitListener(queues = "barista")
    public void process(Order order) {
        log.info("{}", order);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("Interrupted", e);
        }
        log.info("Order[{}] is READY! ", order);
        orderAmqpTemplate.convertAndSend(order);
    }
}
