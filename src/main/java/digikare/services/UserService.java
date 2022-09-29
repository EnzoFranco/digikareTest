package digikare.services;

import digikare.domain.User;
import digikare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Finds all users
     * @return the list of all users
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Find user by id
     * @param id the id
     * @return the user
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Creates an user
     * @param user the user
     * @return the user created
     * @throws Exception the exception threw at creation
     */
    public User createUser(User user) throws Exception {
        try {
            User userSaved = userRepository.save(user);
            return userSaved;
        } catch (Exception e) {
            throw new Exception("Can not save the user");
        }
    }

    /**
     * Deletes an user by id
     * @param id the id
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Deletes all users
     */
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    /**
     * Updates the consents of user
     * @param user the user
     * @return the user updated
     */
    public User updateUserConsents(User user) {
        return userRepository.save(user);
    }

    /**
     * Gets the User Repository
     * @return the user repository
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

}
