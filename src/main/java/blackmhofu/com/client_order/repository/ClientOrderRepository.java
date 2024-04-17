package blackmhofu.com.client_order.repository;

import blackmhofu.com.client_order.model.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientOrderRepository extends JpaRepository<ClientOrder , UUID> {
}
