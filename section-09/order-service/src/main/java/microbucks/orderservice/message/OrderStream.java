package microbucks.orderservice.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderStream {
    @Input
    SubscribableChannel order();

    @Output
    MessageChannel barista();
}
