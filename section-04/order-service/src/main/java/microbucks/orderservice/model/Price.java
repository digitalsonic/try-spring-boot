package microbucks.orderservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash
@Data
@Builder
public class Price {
    @Id
    private String id;
    private String name;
    private String price;
}
