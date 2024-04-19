package blackmhofu.com.order_step.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStepResDto {
    UUID id;
    int  stepNumber;
    LocalDateTime createdAt;
    String description;

}
