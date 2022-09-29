package digikare.repositories;

import digikare.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*@Modifying
    @Query("update User u set u.consents = :consents where u.id = :id")
    User updateConsents (@Param(value = "id") Long id, @Param(value = "consents") Set<Consent> consents);*/
}
