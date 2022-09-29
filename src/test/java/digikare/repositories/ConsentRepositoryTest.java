package digikare.repositories;

import digikare.domain.Consent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class ConsentRepositoryTest {

    @Autowired
    private ConsentRepository consentRepository;

    private Consent consent = new Consent("email_notifications", true);

    /**
     * Tests the add consent of the repository
     */
    @Test
    public void addConsentTest() {
        Consent consentSaved = consentRepository.save(consent);

        assertNotNull(consentSaved);
        assertThat(consentSaved.getId()).isEqualTo("email_notifications");
    }

    /**
     * Tests the get all consents of the repository
     */
    @Test
    public void getAllConsentsTest() {
        consentRepository.save(consent);
        assertEquals(1, consentRepository.findAll().size());
    }
}