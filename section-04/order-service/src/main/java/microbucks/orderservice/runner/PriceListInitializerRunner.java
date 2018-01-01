package microbucks.orderservice.runner;

import microbucks.orderservice.model.Price;
import microbucks.orderservice.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Component
public class PriceListInitializerRunner implements ApplicationRunner {
    public static final String KEY_NAME = "MICROBUCKS_PRICE";
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PriceRepository priceRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Random random = new Random();
        List<String> names = Arrays.asList("latte", "espresso", "mocha", "cappuccino");

        // Set Hashes Directly
        HashMap<String, String> pricelist = new HashMap<>();
        names.forEach(n -> pricelist.put(n,
                Integer.toString(random.nextInt(30) + 20)));
        redisTemplate.opsForHash().putAll(KEY_NAME, pricelist);

        names.forEach(n -> {
            Price p = Price.builder().name(n)
                    .price(Integer.toString(random.nextInt(30) + 20))
                    .build();
            priceRepository.save(p);
        });
    }
}
