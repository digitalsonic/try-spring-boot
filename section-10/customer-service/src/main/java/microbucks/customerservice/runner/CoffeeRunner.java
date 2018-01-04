package microbucks.customerservice.runner;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import microbucks.customerservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
public class CoffeeRunner {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PriceClient priceClient;
    @Autowired
    private OrderClient orderClient;

    @HystrixCommand(fallbackMethod = "fallbackRun")
    public String run() throws Exception {
        String price = getPrice();
        Order order = createOrder(price);
        order = payOrder(order);
        takeCoffee(order);
        return "OK";
    }

    public String fallbackRun() throws Exception {
        return "CLOSED";
    }

    private String getPrice() {
        Map<String, String> price = priceClient.getPriceListDirectly();
        return price.get("latte");
    }

    private Order payOrder(Order order) {
        Order payOrder = Order.builder().price(order.getPrice()).state(1).build();
        orderClient.modifyOrder(order.getId(), payOrder);
        order = orderClient.getOrderById(order.getId());
        log.info("Order after payment: {}", order);
        return order;
    }

    private Order createOrder(String price) {
        Order newOrder = Order.builder().customer("digitalsonic").content("cappuccino").price(price).build();
        Order response = orderClient.createOrder(newOrder);
        log.info("Create Order: {}", response);
        return response;
    }

    private void takeCoffee(Order order) throws InterruptedException {
        boolean taken = false;
        while (!taken) {
            ResponseEntity<Order> responseEntity = orderClient.modifyOrder(order.getId(),
                    Order.builder().state(4).build());
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                log.info("The coffee[{}] has been taken.", responseEntity.getBody());
                break;
            }
            log.info("The coffee is not ready, status code is {}.", responseEntity.getStatusCodeValue());
            Thread.sleep(100);
        }
    }
}