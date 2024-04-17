package blackmhofu.com.step.mapper;


import blackmhofu.com.step.dto.StepResDTO;
import blackmhofu.com.step.model.Step;
import org.springframework.stereotype.Component;

@Component
public class StepMapper {

    public StepResDTO toDo(Step stepToConvert){

        return StepResDTO
                .builder()
                .id(stepToConvert.getId())
                .stepNumber(stepToConvert.getStepNumber())
                .templateName(stepToConvert.getName())
                .description(stepToConvert.getDescription())
                .build();
    }
}
