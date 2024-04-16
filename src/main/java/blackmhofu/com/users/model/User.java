package blackmhofu.com.users.model;


import blackmhofu.com.organisation.model.Organisation;
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
public class User {

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
    private String phoneNumber;
    private String address;
    private UserStatus status;
    private String notes;
    private UserRole role;


    @ManyToOne
    private Organisation organisation;

}
