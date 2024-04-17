package blackmhofu.com.steptemplate.dto;

import java.util.UUID;

public class StepTemplateUpdateDto {
    private UUID organisationId;
    private int numberOfSteps;

    private  String name;
    private String description;

    private  boolean isCompanyDefault;
    private boolean isDefault;
}
