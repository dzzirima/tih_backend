package blackmhofu.com.organisation.model;


import blackmhofu.com.organisation.type.CompanyStatus;
import blackmhofu.com.organisation.type.PaymentStatus;
import blackmhofu.com.users.type.UserRole;
import blackmhofu.com.users.type.UserStatus;
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
public class Organisation {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "UUID"
    )
    private UUID id;
    private String name;

    @Column(
            unique = true
    )
    private String email;
    private String password;
    private  int organisationSize;
    private String industry;
    private String phoneNumber;
    private String address;
    private CompanyStatus companyStatus;
    private PaymentStatus paymentStatus;
    private String notes;

}
