package digikare.services;

import digikare.domain.Event;
import digikare.domain.User;
import digikare.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    private User user = new User("test@gmail.com");

    private Event event1 = new Event();
    private Event event2 = new Event();

    /**
     * Sets up before each test
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user.setId(1L);
        event1.setId(1);
        event1.setUser(user);
        event2.setId(2);
        event2.setUser(user);
    }

    /**
     * Tests the find all events of the service
     */
    @Test
    public void findAllEventsTest() {
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));
        assertEquals(2, eventService.findAllEvents().size());

        verify(eventRepository, times(1)).findAll();
    }

    /**
     * Tests the creation of the event of the service
     * @throws Exception the exception of the createEvent function
     */
    @Test
    public void createEventTest() throws Exception {
        when(eventRepository.save(any(Event.class))).thenReturn(event1);
        assertEquals(event1.getUser().getId(), eventService.createEvent(event1).getUser().getId());
        assertEquals(event1.getId(), eventService.createEvent(event2).getId());

        verify(eventRepository, times(2)).save(any(Event.class));
    }

}