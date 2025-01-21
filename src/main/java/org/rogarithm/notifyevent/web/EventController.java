package org.rogarithm.notifyevent.web;

import org.rogarithm.notifyevent.service.EventService;
import org.rogarithm.notifyevent.service.RecurEventService;
import org.rogarithm.notifyevent.service.dto.EventAddDto;
import org.rogarithm.notifyevent.service.dto.RecurEventAddDto;
import org.rogarithm.notifyevent.web.request.EventAddRequest;
import org.rogarithm.notifyevent.web.request.RecurEventAddRequest;
import org.rogarithm.notifyevent.web.response.EventGetResponse;
import org.rogarithm.notifyevent.web.response.RecurEventAddResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;
    private final RecurEventService recurEventService;

    public EventController(EventService eventService, RecurEventService recurEventService) {
        this.eventService = eventService;
        this.recurEventService = recurEventService;
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
                throw new HttpMessageNotReadableException(request.getEventType().toString());
        }
    }

    @RequestMapping(method=GET, path="/events")
    public List<EventGetResponse> find(
            @RequestParam(name="date", required = false) @DateTimeFormat LocalDate date,
            @RequestParam(name="description", required = false) String description
    ) {
        if (date != null) {
            return eventService.findByDate(date);
        }
        if (description != null) {
            return eventService.findByDescription(description);
        }
        throw new HttpMessageNotReadableException("date and description are required");
    }

    @RequestMapping(method=POST, path="/events/recur")
    public RecurEventAddResponse addRecur(@RequestBody RecurEventAddRequest request) {
        return recurEventService.add(RecurEventAddDto.from(request));
    }
}
