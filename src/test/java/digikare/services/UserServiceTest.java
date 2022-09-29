package digikare.services;

import digikare.domain.User;
import digikare.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1 = new User("test1@gmail.com");
    private User user2 = new User("test2@gmail.com");

    /**
     * Sets up before each test
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user1.setId(1);
        user2.setId(2);
    }

    /**
     * Tests the find all users of the service
     */
    @Test
    public void findAllUsersTest() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        assertEquals(2, userService.findAllUsers().size());

        verify(userRepository, times(1)).findAll();
    }

    /**
     * Tests the get user by id of the service
     */
    @Test
    public void getUserByIdTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user1));
        assertEquals(user1.getEmail(), userService.getUserById(1L).get().getEmail());
    }

    /**
     * Tests the creation of the user of the service
     * @throws Exception the exception of the createUser function
     */
    @Test
    public void createUserTest() throws Exception {
        when(userRepository.save(any(User.class))).thenReturn(user1);
        assertEquals(user1.getEmail(), userService.createUser(user1).getEmail());
        assertEquals(user1.getId(), userService.createUser(user2).getId());

        verify(userRepository, times(2)).save(any(User.class));
    }

    /**
     * Tests the delete user of the service
     */
    @Test
    public void deleteUserTest() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(anyLong());
    }
}