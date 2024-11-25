package org.rogarithm.notifyevent.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class EventRangeTest {

    @DisplayName("당일에 하루종일 지속되는 이벤트 기간을 설정할 수 있다")
    @Test
    public void test_make_1_day_long_event_range() {
        LocalDate aDay = LocalDate.of(2024, 11, 25);
        EventRange oneDayEvt = EventRange.of1Day(aDay);
        assertThat(oneDayEvt.getStartDateTime().toLocalDate()).isEqualTo(aDay);
        assertThat(oneDayEvt.getEndDateTime().toLocalDate()).isEqualTo(aDay);
    }
}
