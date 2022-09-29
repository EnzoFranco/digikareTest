package digikare.controllers;

import digikare.domain.Consent;
import digikare.domain.Event;
import digikare.domain.User;
import digikare.services.EventService;
import digikare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    /**
     * Creates an event
     * @param event the event
     * @return a response entity
     */
    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event) {
       try{
            User user = event.getUser();
            if(userService.getUserById(user.getId()).isPresent()) {
                Set<Consent> consents = event.getConsents();
                for(Consent consent : consents) {
                    String consentId = consent.getId();
                    if(!consentId.equals("email_notifications") && !consentId.equals("sms_notifications")) {
                        return ResponseEntity.unprocessableEntity().body(null);
                    }
                }
                user = userService.getUserById(user.getId()).get();
                Set<Consent> newUserConsents = new HashSet<>();
                for(Consent consent : consents) {
                    for(Consent userConsent : user.getConsents()) {
                        if(consent.equals(userConsent)) {
                            newUserConsents.add(consent);
                        }
                    }
                    if(!newUserConsents.contains(consent)) {
                        newUserConsents.add(consent);
                    }
                }

                event.setDate(new Date());
                user.setConsents(newUserConsents);
                event.setUser(userService.updateUserConsents(user));

                Event eventCreated = eventService.createEvent(event);


                return ResponseEntity.ok().body(eventCreated);
            }
            return ResponseEntity.unprocessableEntity().body(null);

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }

    /**
     * Gets the EventService
     * @return the EventService
     */
    public EventService getEventService() {
        return eventService;
    }

    /**
     * Gets the UserService
     * @return the UserService
     */
    public UserService getUserService() {
        return userService;
    }
}
