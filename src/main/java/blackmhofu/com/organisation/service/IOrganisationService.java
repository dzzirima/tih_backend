package blackmhofu.com.organisation.service;

import blackmhofu.com.organisation.dto.OrganisationReqDTO;
import blackmhofu.com.organisation.dto.OrganisationResDTO;


import blackmhofu.com.organisation.dto.OrganisationUpdateReqDTO;
import blackmhofu.com.organisation.model.Organisation;
import blackmhofu.com.users.dto.UserUpdateReqDTO;


import java.util.ArrayList;
import java.util.UUID;

public interface IOrganisationService {

    public OrganisationResDTO saveOrganisation(OrganisationReqDTO organisationReqDTO);

    public Organisation findById (UUID organisationId); // for linking with other tables
    public OrganisationResDTO findById (String organisationId);  // sending to the client
    public Organisation findByEmail (String  email);  // for authentication

    public String update(OrganisationUpdateReqDTO organisationUpdateReqDTO);

    public  String delete( UUID organisationId);

    public ArrayList<OrganisationResDTO> findAll();
//    public ArrayList<OrganisationResDTO> findByOrganisationId(Long organisationId);

}
