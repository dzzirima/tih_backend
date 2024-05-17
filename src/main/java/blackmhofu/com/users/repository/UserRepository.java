package blackmhofu.com.users.repository;

import blackmhofu.com.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User , UUID> {

    Optional<User> findUserByEmail(String email);

    List<User> findUsersByAgent_Id(UUID agentId);

    boolean existsByName(String name);

    Optional<User> findUserByName(String name);
}
