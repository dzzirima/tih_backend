package blackmhofu.com.organisation.service;

import blackmhofu.com.organisation.dto.OrganisationReqDTO;
import blackmhofu.com.organisation.dto.OrganisationResDTO;
import blackmhofu.com.organisation.dto.OrganisationUpdateReqDTO;
import blackmhofu.com.organisation.mapper.OrganisationMapper;
import blackmhofu.com.organisation.model.Organisation;
import blackmhofu.com.organisation.repository.OrganisationRepository;
import blackmhofu.com.users.dto.UserReqDTO;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.users.dto.UserUpdateReqDTO;
import blackmhofu.com.users.mapper.UserMapper;
import blackmhofu.com.users.model.User;
import blackmhofu.com.users.repository.UserRepository;
import blackmhofu.com.users.service.IUserService;
import blackmhofu.com.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrganisationServiceImpl implements IOrganisationService {
    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private OrganisationMapper organisationMapper;

    @Override
    public OrganisationResDTO saveOrganisation(OrganisationReqDTO organisationReqDTO) {

        Organisation organisationToSave = Organisation
                .builder()
                .email(organisationReqDTO.getEmail())
                .name(organisationReqDTO.getName())
                .password(organisationReqDTO.getPassword())
                .phoneNumber(organisationReqDTO.getPhoneNumber())
                .address(organisationReqDTO.getAddress())
                .companyStatus(organisationReqDTO.getCompanyStatus())
                .paymentStatus(organisationReqDTO.getPaymentStatus())
                .build();


        Organisation savedOrganisation  = organisationRepository.save(organisationToSave);

        return organisationMapper.toDTO(savedOrganisation) ;
    }

    @Override
    public Organisation findById(UUID organisationId) {
        return organisationRepository.findById(organisationId).orElseThrow(() -> new ResourceNotFoundException("organisation with id [ %s ] not found ".formatted(organisationId)));
    }

    @Override
    public OrganisationResDTO findById(String organisationId) {

        Organisation foundOrganisation = findById(UUID.fromString(organisationId));

        return organisationMapper.toDTO(foundOrganisation);
    }

    @Override
    public Organisation findByEmail(String email) {
        return  organisationRepository.findOrganisationByEmail(email).orElseThrow(() -> new ResourceNotFoundException("organisation with email [ %s ] not found ".formatted(email)));
    }

    @Override
    public String update(OrganisationUpdateReqDTO organisationUpdateReqDTO) {
        return null;
    }

    @Override
    public String delete(UUID organisationId) {

        Organisation foundOrganisation = findById(organisationId);

        if (foundOrganisation != null){
            organisationRepository.deleteById(organisationId);
            return "Organisation with id  [ %s ]  was delete successfully ".formatted(organisationId);
        }
        return " Error while deleting Organisation with id  [ %s ]  ".formatted(organisationId);
    }

    @Override
    public ArrayList<OrganisationResDTO> findAll() {

        List<Organisation> foundOrganisations = organisationRepository.findAll();

        List<OrganisationResDTO> collect = foundOrganisations.stream().map(organisation -> organisationMapper.toDTO(organisation)).collect(Collectors.toList());

        return (ArrayList<OrganisationResDTO>) collect;
    }
}
