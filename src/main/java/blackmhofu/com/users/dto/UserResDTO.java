package blackmhofu.com.users.dto;

import blackmhofu.com.users.type.UserRole;
import blackmhofu.com.users.type.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResDTO {

     String email;
     String name;
     String password;
     String phoneNumber;
     String address;
     UserStatus status;
     String notes;
     UserRole role;

     // which org does this client belong to .
     String organisationName;
}
