package blackmhofu.com.organisation.dto;

import blackmhofu.com.organisation.type.CompanyStatus;
import blackmhofu.com.organisation.type.PaymentStatus;
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
public class OrganisationReqDTO {

     String email;
     String name;
     String password;
     String phoneNumber;
     String address;
     CompanyStatus companyStatus;
     PaymentStatus paymentStatus;

}
