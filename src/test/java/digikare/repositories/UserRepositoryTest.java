package digikare.repositories;

import digikare.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user1 = new User("test@abcd.com");
    private User userSaved;

    /**
     * Sets up before each test
     */
    @BeforeEach
    public void setUp() {
        userSaved = userRepository.save(user1);
    }

    /**
     * Tests the add user of the repository
     */
    @Test
    public void addUserTest() {
        User user2 = new User("test2@abcd.com");
        User userSaved2 = userRepository.save(user2);

        assertNotNull(userSaved2);
        assertThat(userSaved2.getId()).isNotEqualTo(null);
    }

    /**
     * Tests the get all users of the repository
     */
    @Test
    public void getAllUsersTest() {
        assertEquals(1, userRepository.findAll().size());
    }

    /**
     * Tests the get user by id of the repository
     */
    @Test
    public void getUserByIDTest() {
        assertNotNull(userRepository.findById(userSaved.getId()));
    }
}