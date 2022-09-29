package digikare.repositories;

import digikare.domain.Event;
import digikare.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    private User user = new User("test@gmail.com");
    private User usersaved;
    private Event event1 = new Event();
    private Event eventSaved;

    /**
     * Sets up before each test
     */
    @BeforeEach
    public void setUp() {
        usersaved = userRepository.save(user);
        event1.setUser(usersaved);
        event1.setDate(new Date());

        eventSaved = eventRepository.save(event1);
    }

    /**
     * Tests the add event of the repository
     */
    @Test
    public void addEventTest() {
        assertNotNull(eventSaved);
        assertThat(eventSaved.getId()).isNotEqualTo(null);
        assertThat(eventSaved.getUser().getId()).isEqualTo(usersaved.getId());
    }

    /**
     * Tests the get all events of the repository
     */
    @Test
    public void getAllEventsTest() {
        assertEquals(1, eventRepository.findAll().size());
    }

    /**
     * Tests the get event by id of the repository
     */
    @Test
    public void getEventByIDTest() {
        assertNotNull(eventRepository.findById(eventSaved.getId()));
    }
}