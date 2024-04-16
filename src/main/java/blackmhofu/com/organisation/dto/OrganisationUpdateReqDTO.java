package blackmhofu.com.organisation.dto;

import blackmhofu.com.organisation.type.CompanyStatus;
import blackmhofu.com.organisation.type.PaymentStatus;
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
public class OrganisationUpdateReqDTO {

     UUID organisationId;
     String name;
     String email;
     String password;
     String phoneNumber;
     String address;
     PaymentStatus paymentStatus;
     CompanyStatus companyStatus;
     String notes;

}
