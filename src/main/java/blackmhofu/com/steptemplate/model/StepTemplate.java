package blackmhofu.com.steptemplate.model;

import blackmhofu.com.organisation.model.Organisation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class StepTemplate {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "UUID"
    )
    private UUID id;

    private  String name;

    private Integer numberOfSteps;

    private String description;

    // to which organisation does it belong to:

    @ManyToOne
    private Organisation organisation;

    private Boolean isDefault;  // global default;

    private  Boolean isCompanyDefault; // default for the company

    private String notes;

}
