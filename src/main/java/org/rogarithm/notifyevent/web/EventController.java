package org.rogarithm.notifyevent.web;

import org.rogarithm.notifyevent.service.EventService;
import org.rogarithm.notifyevent.web.dto.EventAddDto;
import org.rogarithm.notifyevent.web.request.EventAddRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method=POST, path="/event")
    public void add(@RequestBody EventAddRequest request) {
        EventAddDto dto = EventAddDto.of(request.getStartDateTime(), request.getEndDateTime(), request.getDescription());
        eventService.add(dto);
    }
}
