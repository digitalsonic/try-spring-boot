package microbucks.customerservice.runner;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("order-service")
public interface PriceClient {
    @RequestMapping("/price/direct")
    Map<String, String> getPriceListDirectly();
}
