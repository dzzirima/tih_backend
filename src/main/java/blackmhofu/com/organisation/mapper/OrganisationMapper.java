package blackmhofu.com.organisation.mapper;

import blackmhofu.com.organisation.dto.OrganisationResDTO;
import blackmhofu.com.organisation.model.Organisation;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class OrganisationMapper {
    public OrganisationResDTO toDTO(Organisation organisationToConvert){

        return  OrganisationResDTO
                .builder()
                .id(organisationToConvert.getId())
                .name(organisationToConvert.getName())
                .email(organisationToConvert.getEmail())
                .phoneNumber(organisationToConvert.getPhoneNumber())
                .address(organisationToConvert.getAddress())
                .companyStatus(organisationToConvert.getCompanyStatus())
                .paymentStatus(organisationToConvert.getPaymentStatus())
                .notes(organisationToConvert.getNotes())

                .build();
    }
}
