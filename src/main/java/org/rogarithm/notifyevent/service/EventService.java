package org.rogarithm.notifyevent.service;

import org.rogarithm.notifyevent.model.Event;
import org.rogarithm.notifyevent.repository.EventRepository;
import org.rogarithm.notifyevent.service.dto.EventAddDto;
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
        Event event = new Event(dto.getStartDateTime(), dto.getEndDateTime(), dto.getDescription());
        eventRepository.save(event);
    }
}
