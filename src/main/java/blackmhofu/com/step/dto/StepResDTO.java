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
public class StepResDTO {

    private UUID id;
    private String templateName;
    private int stepNumber;
    private String name;
    private String description;
}
