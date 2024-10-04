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

    /**
     * Retrieve all events that match the provided filter
     * @param query the provided filter
     * @return a list of events matching the filter
     */
    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAll();
        // Filter the events list in pure JAVA here
        return events.stream().filter(eventWithBandMemberMatching(query)).collect(Collectors.toList());
    }

    /**
     * Event with band having at least one member matching predicate
     * @param query pattern
     * @return A predicate that allowed to state if a event has a band member with matching pattern
     */
    private Predicate<? super Event> eventWithBandMemberMatching(String query) {
        return event -> event.getBands().stream().anyMatch(bandWithMemberMatching(query));
    }

    /**
     * Band with at least one member matching predicate
     * @param query the pattern to match from
     * @return A predicate that allowed to state if a band has at least one member with matching pattern
     */
    private Predicate<? super Band> bandWithMemberMatching(String query) {
        return band -> band.getMembers().stream().anyMatch(member -> member.getName().toLowerCase().contains(query.toLowerCase()));
    }
}
