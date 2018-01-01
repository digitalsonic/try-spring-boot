package microbucks.orderservice.service;

import microbucks.orderservice.model.Price;
import microbucks.orderservice.repository.PriceRepository;
import microbucks.orderservice.runner.PriceListInitializerRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/price")
public class PriceServiceController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PriceRepository priceRepository;

    @RequestMapping("/direct")
    public Map<String, String> getPriceListDirectly() {
        return redisTemplate.opsForHash().entries(PriceListInitializerRunner.KEY_NAME);
    }

    @RequestMapping("/repo")
    public List<Price> getPriceListFromRepo() {
        List<Price> list = new ArrayList<>();
        priceRepository.findAll().forEach(p -> list.add(p));
        return list;
    }
}
