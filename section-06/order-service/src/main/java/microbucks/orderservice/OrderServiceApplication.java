package microbucks.orderservice;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableRedisRepositories
public class OrderServiceApplication {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @PostConstruct
    public void initQueues() {
        declareAll("barista", "baristaExchange", "barista.*");
        declareAll("order", "orderExchange", "order.*");
    }

    private void declareAll(String qName, String eName, String routeKey) {
        Queue queue = new Queue(qName);
        TopicExchange exchange = new TopicExchange(eName);

        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(routeKey));
    }
}
