package blackmhofu.com.steptemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StepTemplateReqDTO {

    private  UUID organisationId;
    private int numberOfSteps;
    private  String name;
    private String description;
    private  boolean companyDefault;
    private boolean systemDefault;


}
