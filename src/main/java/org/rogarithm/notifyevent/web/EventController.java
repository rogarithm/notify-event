package org.rogarithm.notifyevent.web;

import org.rogarithm.notifyevent.service.EventService;
import org.rogarithm.notifyevent.service.dto.EventAddDto;
import org.rogarithm.notifyevent.web.request.EventAddRequest;
import org.rogarithm.notifyevent.web.response.EventGetResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method=POST, path="/events")
    public void add(@RequestBody EventAddRequest request) {
        switch (request.getEventType()) {
            case HAS_NO_TIME:
                eventService.add(EventAddDto.from(request));
                return;
            case HAS_TIME:
                eventService.add(EventAddDto.from(request));
                return;
            default:
        }
    }

    @RequestMapping(method=GET, path="/events")
    public List<EventGetResponse> find(@RequestParam(name="date") @DateTimeFormat LocalDate date) {
        return eventService.find(date);
    }
}
