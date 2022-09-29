package digikare.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import digikare.domain.Consent;
import digikare.domain.Event;
import digikare.domain.User;
import digikare.services.EventService;
import digikare.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
class EventControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @Mock
    private UserService userService;

    @InjectMocks
    private EventController eventController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    private Event event1 = new Event();
    private User user = new User("test@gmail.com");
    private Consent consent = new Consent("email_notifications", true);

    /**
     * Sets up before each test
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
        user.setId(1L);
        event1.setId(1L);
        event1.setUser(user);

        Set<Consent> consents = new HashSet<>();
        consents.add(consent);
        event1.setConsents(consents);
    }

    /**
     * Tests the creation of the event of the controller
     * @throws Exception the exception of perform method
     */
    @Test
    public void createEvent() throws Exception {
        when(eventService.createEvent(any(Event.class))).thenReturn(event1);
        when(userService.getUserById(anyLong())).thenReturn(java.util.Optional.of(user));

        String content = objectMapper.writeValueAsString(event1);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.user.email", is("test@gmail.com")));


        verify(eventService, times(1)).createEvent(any(Event.class));
    }
}