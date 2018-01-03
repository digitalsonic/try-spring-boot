package microbucks.orderservice.repository;

import microbucks.orderservice.model.Price;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price, String> {
}
