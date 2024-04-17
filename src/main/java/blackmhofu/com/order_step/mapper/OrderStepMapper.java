package blackmhofu.com.order_step.mapper;


import blackmhofu.com.order_step.dto.OrderStepResDto;
import blackmhofu.com.order_step.model.Order_Step;
import org.springframework.stereotype.Component;

@Component
public class OrderStepMapper {
    public OrderStepResDto toDto(Order_Step orderStep){
        return OrderStepResDto
                .builder()
                .orderId(orderStep.getId())
                .stepNumber(orderStep.getStep().getStepNumber())
                .description(orderStep.getDescription())
                .build();
    }
}
