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
import org.rogarithm.notifyevent.web.request.EventType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @InjectMocks
    EventController eventController;

    @Mock
    EventService eventService;

    @Test
    public void test_add_event() {
        LocalDate aDay = LocalDate.of(2024, 11, 25);
        EventAddRequest request = new EventAddRequest(EventType.HAS_NO_TIME, aDay, aDay, null, null, "1 day event");

        eventController.add(request);

        Mockito.verify(eventService, Mockito.times(1)).add(EventAddDto.of2(
                request.getStartDate(),
                request.getEndDate(),
                request.getDescription()
        ));
    }
}
