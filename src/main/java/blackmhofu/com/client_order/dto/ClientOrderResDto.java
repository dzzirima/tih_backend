package blackmhofu.com.client_order.dto;

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
public class ClientOrderResDto {

    UUID id;
    int currentStep;
    GlobalStep globalStep;
    OrderPaymentStatus orderPaymentStatus;

    String customerName;
    String organisation;
    UUID stepTemplateId;
    String address;
    String description;

}
