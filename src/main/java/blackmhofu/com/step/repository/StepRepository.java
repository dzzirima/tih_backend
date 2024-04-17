package blackmhofu.com.step.repository;

import blackmhofu.com.step.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

public interface StepRepository extends JpaRepository<Step , UUID> {
    List<Step> findStepsByTemplate_Id(UUID templateId);
}
