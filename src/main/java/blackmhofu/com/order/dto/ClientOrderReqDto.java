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
public class ClientOrderReqDto {
    int currentStep;
    GlobalStep globalStep;
    OrderPaymentStatus orderPaymentStatus;
    UUID customerId;
    UUID organisationId;
    UUID stepTemplateId;
    String address;
    String description;


    // supporting on the fly orders
    String clientName;
    String phoneNumber;

}
