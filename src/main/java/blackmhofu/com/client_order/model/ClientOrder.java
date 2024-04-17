package blackmhofu.com.client_order.model;


import blackmhofu.com.client_order.type.GlobalStep;
import blackmhofu.com.client_order.type.OrderPaymentStatus;
import blackmhofu.com.organisation.model.Organisation;
import blackmhofu.com.steptemplate.model.StepTemplate;
import blackmhofu.com.users.model.User;
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
public class ClientOrder {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "UUID"
    )
    private UUID id;
    private String notes ;
    private String description;
    private String address ;
    private int currentStep;
    private GlobalStep globalStep;
    private OrderPaymentStatus orderPaymentStatus;

    @ManyToOne
    private Organisation organisation;

    @ManyToOne
    private User customer;
    @ManyToOne
    private StepTemplate stepTemplate;

}
