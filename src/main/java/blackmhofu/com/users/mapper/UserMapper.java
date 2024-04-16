package blackmhofu.com.users.mapper;

import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public  UserResDTO toDTO(User userToConvert){

        return  UserResDTO
                .builder()
                .id(userToConvert.getId())
                .name(userToConvert.getName())
                .email(userToConvert.getEmail())
                .phoneNumber(userToConvert.getPhoneNumber())
                .address(userToConvert.getAddress())
                .status(userToConvert.getStatus())
                .notes(userToConvert.getNotes())
                .role(userToConvert.getRole())
                .organisationName(userToConvert.getOrganisation() != null ? userToConvert.getName() : "N/A ")
                .build();
    }
}
