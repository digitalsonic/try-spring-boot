package microbucks.baristaservice;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BaristaServiceApplication {
	@Autowired
	private RabbitAdmin rabbitAdmin;

	public static void main(String[] args) {
		SpringApplication.run(BaristaServiceApplication.class, args);
	}

	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	public AmqpTemplate orderAmqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setExchange("orderExchange");
		rabbitTemplate.setRoutingKey("order.notify");
		return rabbitTemplate;
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
