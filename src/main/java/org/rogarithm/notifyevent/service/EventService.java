package org.rogarithm.notifyevent.service;

import org.rogarithm.notifyevent.common.exception.ErrorCode;
import org.rogarithm.notifyevent.exception.EventException;
import org.rogarithm.notifyevent.model.Event;
import org.rogarithm.notifyevent.repository.EventRepository;
import org.rogarithm.notifyevent.service.dto.EventAddDto;
import org.rogarithm.notifyevent.web.response.EventGetResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<EventGetResponse> findByDate(LocalDate aDay) {
        List<EventGetResponse> matchingEvents = eventRepository.findAll()
                .stream()
                .filter(evt1 -> evt1.getEventRange().includes(aDay))
                .map(evt -> EventGetResponse.from(evt))
                .collect(Collectors.toList());

        if (matchingEvents.isEmpty()) {
            throw new EventException(ErrorCode.NOT_EXISTS);
        }

        return matchingEvents;
    }

    public List<EventGetResponse> findByDescription(String description) {
        List<EventGetResponse> matchingEvents = eventRepository.findAll()
                .stream()
                .filter(evt -> evt.getDescription().matches("^.*" + description + ".*$"))
                .map(evt -> EventGetResponse.from(evt))
                .collect(Collectors.toList());
        System.out.println(matchingEvents);
        if (matchingEvents.isEmpty()) {
            throw new EventException(ErrorCode.NOT_EXISTS);
        }

        return matchingEvents;
    }
}
