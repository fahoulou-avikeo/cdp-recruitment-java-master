package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.exception.EventNotFoundException;
import adeo.leroymerlin.cdp.helper.EventFormattingHelper;
import adeo.leroymerlin.cdp.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final EventFormattingHelper eventFormattingHelper;

    public EventController(EventService eventService, EventFormattingHelper eventFormattingHelper) {
        this.eventService = eventService;
        this.eventFormattingHelper = eventFormattingHelper;
    }

    @GetMapping(value = "/")
    public List<Event> findEvents() {
        return eventService.getEvents();
    }

    @GetMapping(value = "/search/{query}")
    public List<Event> findEvents(@PathVariable String query) {
        var events = eventService.getFilteredEvents(query);
                events.forEach(eventFormattingHelper::formatEvent);
        return events;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody Event event) {
        try {
            eventService.update(id, event);
        } catch (EventNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found", e);
        }
    }
}
