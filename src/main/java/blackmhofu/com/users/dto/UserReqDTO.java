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
public class UserReqDTO {

     String email;
     String name;
     String password;
     String phoneNumber;
     String address;
     UserStatus status;
     String notes;
     UserRole role;

     // which org does this client belong to .
     UUID organisationId;
}
