package blackmhofu.com.step.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StepUpdateReqDTO {

    private UUID stepId;
    private UUID templatedId;
    private int stepNumber;
    private String name;
    private String description;
}
