package digikare;

import digikare.domain.Consent;
import digikare.domain.User;
import digikare.repositories.ConsentRepository;
import digikare.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ConsentRepository consentRepository;

    @Override
    public void run(String...args) throws Exception {

        if(userService.findAllUsers().isEmpty()) {
            User user1 = new User("test@gmail.com");
            User user2 = new User("anthony@gmail.com");
            User user3 = new User("johan@digikare.com");
            User user4 = new User("john.doe@digikare.com");

            userService.createUser(user1);
            userService.createUser(user2);
            userService.createUser(user3);
            userService.createUser(user4);
        }

        if(consentRepository.findAll().isEmpty()) {
            Consent consent1 = new Consent("email_notifications", true);
            Consent consent2 = new Consent("email_notifications", false);
            Consent consent3 = new Consent("sms_notifications", true);
            Consent consent4 = new Consent("sms_notifications", false);

            consentRepository.save(consent1);
            consentRepository.save(consent2);
            consentRepository.save(consent3);
            consentRepository.save(consent4);
        }

    }
}
