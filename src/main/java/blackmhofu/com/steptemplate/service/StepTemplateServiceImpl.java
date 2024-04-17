package blackmhofu.com.steptemplate.service;

import blackmhofu.com.organisation.model.Organisation;
import blackmhofu.com.organisation.service.OrganisationServiceImpl;
import blackmhofu.com.steptemplate.dto.StepTemplateReqDTO;
import blackmhofu.com.steptemplate.dto.StepTemplateResDTO;
import blackmhofu.com.steptemplate.dto.StepTemplateUpdateDto;
import blackmhofu.com.steptemplate.mapper.StepTemplateMapper;
import blackmhofu.com.steptemplate.model.StepTemplate;
import blackmhofu.com.steptemplate.repository.StepTemplateServiceRepository;
import blackmhofu.com.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StepTemplateServiceImpl implements IStepTemplateService {

    @Autowired
    private StepTemplateServiceRepository stepServiceRepository;

    @Autowired
    private OrganisationServiceImpl organisationService;

    @Autowired
    private StepTemplateMapper stepTemplateMapper;

    @Override
    public StepTemplateResDTO saveStepTemplate(StepTemplateReqDTO stepTemplateReqDTO) {

        Organisation foundOrganisation = organisationService.findById(stepTemplateReqDTO.getOrganisationId());

        StepTemplate stepTemplateToSave = StepTemplate
                .builder()
                .isCompanyDefault(stepTemplateReqDTO.isCompanyDefault())
                .isDefault(stepTemplateReqDTO.isDefault())
                .numberOfSteps(stepTemplateReqDTO.getNumberOfSteps())
                .organisation(foundOrganisation)
                .description(stepTemplateReqDTO.getDescription())
                .name(stepTemplateReqDTO.getName())
                .build();


        StepTemplate savedStepTemplate = stepServiceRepository.save(stepTemplateToSave);

        return stepTemplateMapper.toDto(savedStepTemplate);


    }

    @Override
    public StepTemplateResDTO findById(UUID stepTemplateId) {
        StepTemplate foundStepTemplate = stepServiceRepository.findById(stepTemplateId).orElseThrow(() -> new ResourceNotFoundException("step template with id  [ %s ]  not found".formatted(stepTemplateId)));
        return stepTemplateMapper.toDto(foundStepTemplate);
    }

    @Override
    public StepTemplate findById(String stepTemplateId) {
        return stepServiceRepository.findById(UUID.fromString(stepTemplateId)).orElseThrow(() -> new ResourceNotFoundException("step template with id  [ %s ]  not found".formatted(stepTemplateId)));
    }

    @Override
    public String Update(StepTemplateUpdateDto stepTemplateUpdateDto) {
        return null;
    }

    @Override
    public String delete(UUID stepTemplateId) {

        StepTemplate foundStepTemplate = findById(stepTemplateId.toString());
        if (foundStepTemplate != null) {
            stepServiceRepository.deleteById(stepTemplateId);
            return "Successfully deleted StepTemplate id [ %s ] ".formatted(stepTemplateId);
        }
        return "Error while deleting a stepTemplate with id  [ %s ] ".formatted(stepTemplateId);
    }

    @Override
    public List<StepTemplateResDTO> findAll() {

        return stepServiceRepository.findAll().stream().map(stepTemplate -> stepTemplateMapper.toDto(stepTemplate)).collect(Collectors.toList());
    }

    @Override
    public List<StepTemplateResDTO> findAllByCompanyId(UUID companyId) {

        List<StepTemplate> foundStepTemplate = stepServiceRepository.findStepTemplateByOrganisation_Id(companyId);

        return foundStepTemplate.stream().map(stepTemplate -> stepTemplateMapper.toDto(stepTemplate)).collect(Collectors.toList());

    }
}
