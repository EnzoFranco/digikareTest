package digikare.services;

import digikare.domain.Event;
import digikare.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Create the event in the database
     * @param event the event
     * @return the event entity persisted
     */
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    /**
     * Gets the EventRepository
     * @return the EventRepository
     */
    public EventRepository getEventRepository() {
        return eventRepository;
    }

    /**
     * Finds all events
     * @return the list of all events
     */
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }
}
