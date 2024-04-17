package blackmhofu.com.media.model;



import blackmhofu.com.order_step.model.Order_Step;
import blackmhofu.com.utils.type.Executor;
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
public class Media {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "UUID"
    )
    private UUID id;
    private String name;
    private Long size;

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

    @Embedded
    private Executor executor;

    @ManyToOne
    private Order_Step order_step;

}
