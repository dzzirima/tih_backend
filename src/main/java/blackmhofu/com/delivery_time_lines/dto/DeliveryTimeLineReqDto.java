package blackmhofu.com.delivery_time_lines.dto;

import blackmhofu.com.client_order.type.GlobalStep;
import blackmhofu.com.client_order.type.OrderPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryTimeLineReqDto {

    private  String  deliveryUpDates;;


    private GlobalStep globalStep;
    private OrderPaymentStatus orderPaymentStatus;

    private UUID clientOrderId;

}
