package digikare.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import digikare.domain.User;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    private User user1 = new User("test1@gmail.com");
    private User user2 = new User("test2@gmail.com");

    /**
     * Sets up before each test
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user1.setId(1L);
        user2.setId(2L);
    }

    /**
     * Tests the get all users of the controller
     * @throws Exception the exception of perform method
     */
    @Test
    public void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));

        when(userService.findAllUsers()).thenReturn(users);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].email", is("test2@gmail.com")));

        verify(userService, times(1)).findAllUsers();

    }

    /**
     * Tests the get user by id of the controller
     * @throws Exception the exception of perform method
     */
    @Test
    public void getUserById() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(java.util.Optional.of(user1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.email", is("test1@gmail.com")));

        verify(userService, times(1)).getUserById(anyLong());
    }

    /**
     * Tests the creation of the user of the controller
     * @throws Exception the exception of perform method
     */
    @Test
    public void createUser() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(user1);

        String content = objectMapper.writeValueAsString(user1);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.email", is("test1@gmail.com")));


        when(userService.createUser(any(User.class))).thenThrow(Exception.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isUnprocessableEntity());
        verify(userService, times(2)).createUser(any(User.class));
    }

    /**
     * Tests the delete user by id of the controller
     * @throws Exception the exception of perform method
     */
    @Test
    public void deleteUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(anyLong());
    }
}