package blackmhofu.com.client_order.dto;

import blackmhofu.com.client_order.type.GlobalStep;
import blackmhofu.com.client_order.type.OrderPaymentStatus;
import blackmhofu.com.order_step.dto.OrderStepResDto;
import blackmhofu.com.step.dto.StepResDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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

    List<StepResDTO> templateSteps;
    List<OrderStepResDto> currentSteps;

}
