package blackmhofu.com.step.service;

import blackmhofu.com.step.dto.StepReqDTO;
import blackmhofu.com.step.dto.StepResDTO;
import blackmhofu.com.step.dto.StepUpdateReqDTO;
import blackmhofu.com.step.model.Step;

import java.util.List;
import java.util.UUID;

public interface IStepService {
    public StepResDTO save(StepReqDTO stepReqDTO) ;

    public Step findById(UUID stepId);
    public StepResDTO findById(String stepId);

    public  List<StepResDTO> findAllByTemplateId(UUID templateId);

    public  String  delete( UUID stepNumber);

    public  String update (StepUpdateReqDTO updateReqDTO);

}
