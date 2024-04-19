package blackmhofu.com.steptemplate.service;

import blackmhofu.com.steptemplate.dto.StepTemplateReqDTO;
import blackmhofu.com.steptemplate.dto.StepTemplateResDTO;
import blackmhofu.com.steptemplate.dto.StepTemplateUpdateDto;
import blackmhofu.com.steptemplate.model.StepTemplate;

import java.util.List;
import java.util.UUID;

public interface IStepTemplateService {

    public StepTemplateResDTO saveStepTemplate(StepTemplateReqDTO stepTemplateReqDTO);
    public StepTemplateResDTO findById(UUID stepTemplateId);
    public StepTemplate findById(String stepTemplateId);
    public String Update(StepTemplateUpdateDto stepTemplateUpdateDto);
    public String delete(UUID stepTemplateId);
    public  List<StepTemplateResDTO> findAll();
    public List<StepTemplateResDTO> findAllByOrganisationId(UUID companyId);

}
