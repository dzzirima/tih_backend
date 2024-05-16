package blackmhofu.com.users.dto;

import blackmhofu.com.users.type.UserRole;
import blackmhofu.com.users.type.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResDTO {

     UUID id;
     String email;
     String name;
     String phoneNumber;
     String address;
     UserStatus status;
     String notes;
     UserRole role;

     double balance = 10000.01;

     // which org does this client belong to .
     String organisationName;
}
