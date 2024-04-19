package blackmhofu.com.step.model;


import blackmhofu.com.steptemplate.model.StepTemplate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Step {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "UUID"
    )
    private UUID id;
    private String name;
    private  Integer stepNumber;
    private String description;

    @ManyToOne
    private StepTemplate template;

    // timestamps

    @CreationTimestamp
    @Column(
            nullable = false ,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
            insertable = false
    )
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
