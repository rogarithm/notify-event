package org.rogarithm.notifyevent.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rogarithm.notifyevent.service.EventService;
import org.rogarithm.notifyevent.web.dto.EventAddDto;
import org.rogarithm.notifyevent.web.request.EventAddRequest;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @InjectMocks
    EventController eventController;

    @Mock
    EventService eventService;

    @Test
    public void test_add_event() {
        LocalDateTime aDay = LocalDateTime.of(2024, 11, 25, 0, 0, 0);
        EventAddRequest request = new EventAddRequest(aDay, aDay, "1 day event");

        eventController.add(request);

        Mockito.verify(eventService, Mockito.times(1)).add(EventAddDto.of(
                request.getStartDateTime(),
                request.getEndDateTime(),
                request.getDescription()
        ));
    }
}
