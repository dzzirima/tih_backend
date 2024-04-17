package blackmhofu.com.order_step.service;

import blackmhofu.com.order_step.dto.OrderStepReqDto;
import blackmhofu.com.order_step.dto.OrderStepResDto;
import blackmhofu.com.order_step.model.Order_Step;

import java.util.List;
import java.util.UUID;

public interface IOrderStepService {

    public OrderStepResDto save (OrderStepReqDto orderStepReqDto);
    public List<OrderStepResDto> findAllByOrderId(UUID orderId);

    public  Order_Step findById(UUID orderStepId);


}
