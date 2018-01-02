package microbucks.orderservice.repository;

import microbucks.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findTop1ByBaristaOrderById(String barista);
    List<Order> findByCustomerOrderById(String customer);
}
