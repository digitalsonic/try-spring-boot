package microbucks.orderservice;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@AutoConfigureAfter(PropertyPlaceholderAutoConfiguration.class)
public class OrderServiceAutoConfiguration {

    @Bean
    @ConditionalOnBean(PropertySourcesPlaceholderConfigurer.class)
    public BaristaConfigBean baristaConfigBean() {
        return new BaristaConfigBean();
    }
}
