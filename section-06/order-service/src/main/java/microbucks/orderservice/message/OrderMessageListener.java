package microbucks.orderservice.message;

import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import microbucks.orderservice.model.Status;
import microbucks.orderservice.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class OrderMessageListener {
    @Autowired
    private OrderRepository orderRepository;

    @RabbitListener(queues = "order")
    public void process(Order order) {
        Order savedOrder = orderRepository.findOne(order.getId());
        savedOrder.setState(Status.READY);
        savedOrder.setModifyTime(new Date());
        orderRepository.save(savedOrder);
        log.info("Order is READY! {}", savedOrder);
    }
}
