package blackmhofu.com.steptemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class StepTemplateResDTO {
    private UUID id;
    private String organisation;
    private int numberOfSteps;

    private  String name;
    private String description;

    private  boolean isCompanyDefault;
    private boolean isDefault;
}
