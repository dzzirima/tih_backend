package blackmhofu.com.client_order.repository;

import blackmhofu.com.client_order.model.ClientOrder;
import blackmhofu.com.media.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ClientOrderRepository extends JpaRepository<ClientOrder , UUID> {

    List<ClientOrder> findClientOrdersByAgent_Id(UUID agentId);


}
