package blackmhofu.com.order_step.repository;

import blackmhofu.com.order_step.model.Order_Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderStepRepository extends JpaRepository<Order_Step , UUID> {

    List<Order_Step> findOrder_StepsByOrder_Id(UUID clientOrderId);


}
