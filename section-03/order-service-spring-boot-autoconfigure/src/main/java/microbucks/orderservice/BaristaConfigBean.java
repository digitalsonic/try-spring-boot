package microbucks.orderservice;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class BaristaConfigBean {
    @Value("${barista.prefix:D_Barista}")
    private String baristaPrefix;
    @Value("${random.int}")
    private int random;
    private String barista;

    @PostConstruct
    public void initBarista() {
        barista = baristaPrefix + "_" + random;
    }

    public String getBarista() {
        return barista;
    }
}
