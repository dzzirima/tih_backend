package blackmhofu.com.step.service;

import blackmhofu.com.step.dto.StepReqDTO;
import blackmhofu.com.step.dto.StepResDTO;
import blackmhofu.com.step.dto.StepUpdateReqDTO;
import blackmhofu.com.step.mapper.StepMapper;
import blackmhofu.com.step.model.Step;
import blackmhofu.com.step.repository.StepRepository;
import blackmhofu.com.steptemplate.model.StepTemplate;
import blackmhofu.com.steptemplate.repository.StepTemplateServiceRepository;
import blackmhofu.com.steptemplate.service.StepTemplateServiceImpl;
import blackmhofu.com.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class StepServiceImpl implements IStepService {


    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private StepMapper stepMapper;

    @Autowired
    private StepTemplateServiceImpl stepTemplateService;

    @Override
    public StepResDTO save(StepReqDTO stepReqDTO) {

        StepTemplate foundStepTemplate = stepTemplateService.findById(stepReqDTO.getTemplatedId() .toString());

        Step stepToBeSaved = Step
                .builder()
                .name(stepReqDTO.getName())
                .template(foundStepTemplate)
                .stepNumber(stepReqDTO.getStepNumber())
                .description(stepReqDTO.getDescription())
                .build();
        Step savedStep = stepRepository.save(stepToBeSaved);
        return stepMapper.toDo(savedStep);
    }

    @Override
    public Step findById(UUID stepId) {


        Step foundStep = stepRepository.findById(stepId).orElseThrow(() -> new ResourceNotFoundException("Step not found  with id  [ %s ]".formatted(stepId)));


        return foundStep;
    }

    @Override
    public StepResDTO findById(String stepId) {

        Step foundStep = findById(UUID.fromString(stepId));


        return stepMapper.toDo(foundStep);
    }

    @Override
    public List<StepResDTO> findAllByTemplateId(UUID templateId) {

        /*List<Step> foundSteps = stepRepository.findStepsByTemplate_Id(templateId);*/


        List<Step> foundSteps = stepRepository.findStepsByTemplateId(templateId);
        return foundSteps.stream().map(step -> stepMapper.toDo(step)).collect(Collectors.toList());
    }

    @Override
    public Step findByTemplateIdAndStepNumber(UUID templateId, Integer stepNumber) {
        Step foundStep = stepRepository.findByTemplateIdAndStepNumber(templateId, stepNumber).orElseThrow(() -> new ResourceNotFoundException("Step not found  with id  [ %s ]".formatted(templateId)));

        return  foundStep;
    }

    @Override
    public String delete(UUID stepNumber) {

        Step foundStep = findById(stepNumber);

        if (foundStep != null) {
            stepRepository.deleteById(stepNumber);
            return "Step with id  [ %s ]  was successfully deleted ".formatted(stepNumber);
        }


        return " Error while deleting Step with id  [ %s ] ".formatted(stepNumber);
    }

    @Override
    public String update(StepUpdateReqDTO updateReqDTO) {
        return null;
    }
}
