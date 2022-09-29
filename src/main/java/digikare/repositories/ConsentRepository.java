package digikare.repositories;

import digikare.domain.Consent;
import digikare.domain.ConsentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, ConsentId> {
}
