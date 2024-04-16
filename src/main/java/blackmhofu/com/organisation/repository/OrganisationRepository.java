package blackmhofu.com.organisation.repository;

import blackmhofu.com.organisation.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganisationRepository extends JpaRepository<Organisation, UUID> {

    Optional<Organisation>  findOrganisationByEmail(String email);


}
