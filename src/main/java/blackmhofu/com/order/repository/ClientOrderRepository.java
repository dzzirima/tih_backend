package blackmhofu.com.order.repository;

import blackmhofu.com.order.model.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientOrderRepository extends JpaRepository<ClientOrder , UUID> {
}
