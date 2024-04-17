package blackmhofu.com.steptemplate.mapper;


import blackmhofu.com.steptemplate.dto.StepTemplateReqDTO;
import blackmhofu.com.steptemplate.dto.StepTemplateResDTO;
import blackmhofu.com.steptemplate.model.StepTemplate;
import org.springframework.stereotype.Component;

@Component
public class StepTemplateMapper {

    public StepTemplateResDTO toDto(StepTemplate stepTemplateToConvert) {

        return  StepTemplateResDTO
                .builder()
                .isCompanyDefault(stepTemplateToConvert.getIsCompanyDefault())
                .isDefault(stepTemplateToConvert.getIsDefault())
                .numberOfSteps(stepTemplateToConvert.getNumberOfSteps())
                .organisation(stepTemplateToConvert.getOrganisation().getName())
                .description(stepTemplateToConvert.getDescription())
                .name(stepTemplateToConvert.getName())
                .id(stepTemplateToConvert.getId())
                .build();
    }
}
