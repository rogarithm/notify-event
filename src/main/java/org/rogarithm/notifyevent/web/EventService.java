package org.rogarithm.notifyevent.web;

import org.rogarithm.notifyevent.model.Event;
import org.rogarithm.notifyevent.model.EventRepository;
import org.rogarithm.notifyevent.web.dto.EventAddDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void add(EventAddDto dto) {
        Event event = Event.of1Day(dto.getStartDateTime().toLocalDate(), dto.getDescription());
        eventRepository.save(event);
        System.out.println("HERE: " + event.getId());
    }
}
