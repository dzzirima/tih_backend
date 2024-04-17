package blackmhofu.com.order_step.model;


//import blackmhofu.com.order.model.Order;
import blackmhofu.com.client_order.model.ClientOrder;
import blackmhofu.com.step.model.Step;
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

public class Order_Step {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "UUID"
    )
    private UUID id;
    private String description;

    @ManyToOne
    private Step step;

    @ManyToOne
    private ClientOrder order;



}
