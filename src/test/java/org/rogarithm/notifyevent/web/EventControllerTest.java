package org.rogarithm.notifyevent.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rogarithm.notifyevent.model.Event;
import org.rogarithm.notifyevent.service.EventService;
import org.rogarithm.notifyevent.service.RecurEventService;
import org.rogarithm.notifyevent.service.dto.EventAddDto;
import org.rogarithm.notifyevent.web.request.*;
import org.rogarithm.notifyevent.web.response.EventGetResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @InjectMocks
    EventController eventController;

    @Mock
    EventService eventService;
    @Mock
    RecurEventService recurEventService;

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

        @DisplayName("반복되는 이벤트 등록 요청을 만들 수 있다")
        @Test
        public void test_add_recur_event() throws JsonProcessingException {
            RecurEventAddRequest request = new RecurEventAddRequest(
                    "golf games", RecurType.DAY_IN_MONTH,
                    new RecurParams("1", "1")
            );

            eventController.addRecur(request);
        }
    }

    @Nested
    @DisplayName("리소스 읽어오기 핸들러")
    class HandleGet {
        @DisplayName("특정 일자의 이벤트 목록을 가져올 수 있다")
        @Test
        public void test_get_events_by_date() {
            LocalDate aDay = LocalDate.of(2024, 11, 25);
            Mockito.when(eventService.findByDate(aDay)).thenReturn(List.of(
                    EventGetResponse.from(new Event(
                            LocalDateTime.of(aDay, LocalTime.of(9,0)),
                            LocalDateTime.of(aDay, LocalTime.of(10,0)),
                            "check1")
                    ),
                    EventGetResponse.from(new Event(
                            LocalDateTime.of(aDay, LocalTime.of(12,0)),
                            LocalDateTime.of(aDay, LocalTime.of(14,0)),
                            "check2")
                    )
            ));

            List<EventGetResponse> matchingEvents = eventController.find(aDay, null);

            Mockito.verify(eventService, Mockito.times(1)).findByDate(aDay);
            assertThat(
                    matchingEvents.stream().map(res -> res.getStartDateTime().toLocalDate())
            ).isEqualTo(
                    List.of(aDay, aDay)
            );
        }

        @DisplayName("주어진 이벤트 상세 정보를 갖는 이벤트 목록을 가져올 수 있다")
        @Test
        public void test_get_events_by_description() {
            LocalDate aDay = LocalDate.of(2024, 11, 25);
            String description = "check";
            Mockito.when(eventService.findByDescription(description)).thenReturn(List.of(
                    EventGetResponse.from(new Event(
                            LocalDateTime.of(aDay, LocalTime.of(9,0)),
                            LocalDateTime.of(aDay, LocalTime.of(10,0)),
                            "check1")
                    ),
                    EventGetResponse.from(new Event(
                            LocalDateTime.of(aDay, LocalTime.of(12,0)),
                            LocalDateTime.of(aDay, LocalTime.of(14,0)),
                            "check2")
                    )
            ));

            List<EventGetResponse> matchingEvents = eventController.find(null, description);

            Mockito.verify(eventService, Mockito.times(1)).findByDescription(description);
            assertThat(
                    matchingEvents.stream().map(res -> res.getDescription())
            ).isEqualTo(
                    List.of("check1", "check2")
            );
        }
    }
}
