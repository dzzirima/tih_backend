package blackmhofu.com.delivery_time_lines.mapper;


import blackmhofu.com.delivery_time_lines.dto.DeliveryTimeLineResDto;
import blackmhofu.com.delivery_time_lines.model.DeliveryTimeLine;
import org.springframework.stereotype.Component;

@Component
public class DeliveryTimeLineMapper {

    public DeliveryTimeLineResDto todo(DeliveryTimeLine deliveryTimeLine){

        return  DeliveryTimeLineResDto
                .builder()
                .clientOrderId(deliveryTimeLine.getId())
                .deliveryUpDates(deliveryTimeLine.getDescription())
                .globalStep(deliveryTimeLine.getGlobalStep())
                .orderPaymentStatus(deliveryTimeLine.getOrderPaymentStatus())

                .createdAt(deliveryTimeLine.getCreatedAt())
                .build();
    }
}
