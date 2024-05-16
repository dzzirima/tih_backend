package blackmhofu.com.delivery_time_lines.repository;

import blackmhofu.com.delivery_time_lines.model.DeliveryTimeLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryTimeLineRepository extends JpaRepository<DeliveryTimeLine , UUID> {

    List<DeliveryTimeLine> findDeliveryTimeLinesByClientOrder_Id(UUID clientOrderId);
}
