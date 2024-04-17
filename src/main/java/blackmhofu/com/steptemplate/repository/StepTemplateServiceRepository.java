package blackmhofu.com.steptemplate.repository;

import blackmhofu.com.step.model.Step;
import blackmhofu.com.steptemplate.model.StepTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StepTemplateServiceRepository extends JpaRepository<StepTemplate, UUID> {

    List<StepTemplate> findStepTemplateByOrganisation_Id(UUID organisationId);


}
