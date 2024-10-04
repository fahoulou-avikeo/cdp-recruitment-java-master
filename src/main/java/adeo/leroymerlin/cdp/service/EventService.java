package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.entity.Band;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.exception.EventNotFoundException;
import adeo.leroymerlin.cdp.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Get all events
     * @return a list of all the events
     */
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    /**
     * Delete an event by its identifier
     * @param id the event identifier
     */
    @Transactional
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    /**
     * Update an event in database
     * @param id the event identifier
     * @param event the data to use for update
     */
    @Transactional
    public void update(Long id, Event event) {
        var existingEventOpt = eventRepository.findById(id);
        if (existingEventOpt.isEmpty()) {
            throw new EventNotFoundException();
        }
        var existingEvent = existingEventOpt.get();
        existingEvent.setComment(event.getComment());
        existingEvent.setNbStars(event.getNbStars());
        eventRepository.save(existingEvent);
    }
}
