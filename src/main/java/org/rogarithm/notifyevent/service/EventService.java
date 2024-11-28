package org.rogarithm.notifyevent.service;

import org.rogarithm.notifyevent.model.Event;
import org.rogarithm.notifyevent.repository.EventRepository;
import org.rogarithm.notifyevent.service.dto.EventAddDto;
import org.rogarithm.notifyevent.web.response.EventGetResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Transactional
    public List<EventGetResponse> find(LocalDate aDay) {
        return eventRepository.findAll()
                .stream()
                .filter(evt1 -> evt1.getEventRange().includes(aDay))
                .map(evt -> EventGetResponse.from(evt))
                .collect(Collectors.toList());
    }
}
