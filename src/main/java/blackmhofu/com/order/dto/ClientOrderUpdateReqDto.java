package blackmhofu.com.order.dto;

import blackmhofu.com.order.type.GlobalStep;
import blackmhofu.com.order.type.OrderPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientOrderUpdateReqDto {

    UUID orderId;
    int currentStep;
    GlobalStep globalStep;
    OrderPaymentStatus orderPaymentStatus;
    UUID stepTemplateId;
    String address;
    String description;
}
