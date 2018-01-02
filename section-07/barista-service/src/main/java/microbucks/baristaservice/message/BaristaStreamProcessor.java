package microbucks.baristaservice.message;

import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BaristaStreamProcessor {
    public static final int READY_STATE = 3;
    @Value("${random.int}")
    private int baristaNo;

    @StreamListener("barista")
    @SendTo("order")
    public Order receiveOrder(Order order) {
        log.info("receive order: {}", order);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        log.info("coffee for order {} is ready", order.getId());
        order.setBarista("Barista" + baristaNo);
        order.setState(READY_STATE);
        return order;
    }
}
