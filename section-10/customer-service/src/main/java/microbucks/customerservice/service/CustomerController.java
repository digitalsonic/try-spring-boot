package microbucks.customerservice.service;

import microbucks.customerservice.runner.CoffeeRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CoffeeRunner coffeeRunner;

    @RequestMapping("/buy")
    public String buyCoffee() throws Exception {
        return coffeeRunner.run();
    }
}
