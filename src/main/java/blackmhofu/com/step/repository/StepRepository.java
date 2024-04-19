package blackmhofu.com.step.repository;

import blackmhofu.com.step.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StepRepository extends JpaRepository<Step , UUID> {
    List<Step> findStepsByTemplate_Id(UUID templateId);




    @Query("select u from Step u where u.template.id = ?1")
    List<Step> findStepsByTemplateId(UUID templateId);
}
