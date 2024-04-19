package blackmhofu.com.order_step.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStepReqDto {
    UUID orderId;
    UUID stepId;
    String description;
    String attachedMediaIdsList;
}
