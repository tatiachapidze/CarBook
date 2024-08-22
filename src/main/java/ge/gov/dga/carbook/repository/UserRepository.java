package ge.gov.dga.carbook.repository;

import ge.gov.dga.carbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserMail(String userMail);

    boolean existsByUsername(String username);
}
