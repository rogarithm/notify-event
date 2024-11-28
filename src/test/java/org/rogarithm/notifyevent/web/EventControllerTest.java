package org.rogarithm.notifyevent.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rogarithm.notifyevent.service.EventService;
import org.rogarithm.notifyevent.service.dto.EventAddDto;
import org.rogarithm.notifyevent.web.request.EventAddRequest;
import org.rogarithm.notifyevent.web.request.EventType;

import java.time.LocalDate;
import java.time.LocalTime;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @InjectMocks
    EventController eventController;

    @Mock
    EventService eventService;

    @Nested
    @DisplayName("리소스 추가 핸들러")
    class HandlePost {
        @DisplayName("하루짜리 종일 이벤트를 추가할 수 있다")
        @Test
        public void test_add_1day_event() {
            LocalDate aDay = LocalDate.of(2024, 11, 25);
            EventAddRequest request = new EventAddRequest(
                    EventType.HAS_NO_TIME,
                    aDay, aDay,
                    null, null,
                    "1 day event"
            );

            eventController.add(request);

            Mockito.verify(eventService, Mockito.times(1)).add(EventAddDto.from(request));
        }

        @DisplayName("하루 중 일부에 시작하고 끝나는 이벤트를 추가할 수 있다")
        @Test
        public void test_add_1day_partial_event() {
            LocalDate aDay = LocalDate.of(2024, 11, 25);
            LocalTime startTime = LocalTime.of(20, 0);
            LocalTime endTime = LocalTime.of(21, 0);
            EventAddRequest request = new EventAddRequest(
                    EventType.HAS_TIME,
                    aDay, aDay,
                    startTime, endTime,
                    "1 day partial event"
            );

            eventController.add(request);

            Mockito.verify(eventService, Mockito.times(1)).add(EventAddDto.from(request));
        }

        @DisplayName("여러 날 짜리 종일 이벤트를 추가할 수 있다")
        @Test
        public void test_add_x_day_event() {
            LocalDate startDay = LocalDate.of(2024, 11, 25);
            LocalDate endDay = LocalDate.of(2024, 11, 30);
            EventAddRequest request = new EventAddRequest(
                    EventType.HAS_NO_TIME,
                    startDay, endDay,
                    null, null,
                    "x day event"
            );

            eventController.add(request);

            Mockito.verify(eventService, Mockito.times(1)).add(EventAddDto.from(request));
        }

        @DisplayName("시작 시간과 종료 시간이 있고 여러 날이 포함되는 이벤트를 추가할 수 있다")
        @Test
        public void test_add_x_day_partial_event() {
            LocalDate startDay = LocalDate.of(2024, 11, 25);
            LocalDate endDay = LocalDate.of(2024, 11, 30);
            LocalTime startTime = LocalTime.of(20, 0);
            LocalTime endTime = LocalTime.of(21, 0);
            EventAddRequest request = new EventAddRequest(
                    EventType.HAS_TIME,
                    startDay, endDay,
                    startTime, endTime,
                    "1 day partial event"
            );

            eventController.add(request);

            Mockito.verify(eventService, Mockito.times(1)).add(EventAddDto.from(request));
        }
    }

    @Nested
    @DisplayName("리소스 읽어오기 핸들러")
    class HandleGet {
    }
}
