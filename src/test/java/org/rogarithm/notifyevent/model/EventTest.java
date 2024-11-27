package org.rogarithm.notifyevent.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rogarithm.notifyevent.web.dto.EventAddDto;
import org.rogarithm.notifyevent.web.request.EventAddRequest;
import org.rogarithm.notifyevent.web.request.EventType;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @DisplayName("당일에 하루종일 지속되는 이벤트를 만들 수 있다")
    @Test
    public void test_make_1_day_long_event() {
        LocalDate aDay = LocalDate.of(2024, 11, 25);
        Event oneDayEvt = Event.of1Day(aDay, "1 day event");
        assertThat(oneDayEvt.getEventRange()).isNotNull();
        assertThat(oneDayEvt.getDescription()).isNotNull();
    }
}
