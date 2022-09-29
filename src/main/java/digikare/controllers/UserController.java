package digikare.controllers;

import digikare.domain.User;
import digikare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Gets all users
     * @return the list of all users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * Gets an user by id
     * @param id the id
     * @return the user or null
     */
    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

    /**
     * Creates one user
     * @param user the user
     * @return a response entity
     */
    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            User userCreated = userService.createUser(user);
            return ResponseEntity.ok().body(userCreated);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /**
     * Deletes an user by id
     * @param id the id
     */
    @DeleteMapping(value = "/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Gets the User Service
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }
}
