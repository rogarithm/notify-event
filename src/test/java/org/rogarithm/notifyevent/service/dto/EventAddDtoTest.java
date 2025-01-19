package org.rogarithm.notifyevent.service.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rogarithm.notifyevent.web.request.EventAddRequest;
import org.rogarithm.notifyevent.web.request.EventType;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class EventAddDtoTest {

    @DisplayName("하루동안 지속되는 이벤트일 때 이벤트의 시작/끝 시간을 설정한다")
    @Test
    public void test_setting_time_of_1_day_event() {
        LocalDate oneDay = LocalDate.of(2024, 11, 25);
        EventAddRequest request = new EventAddRequest(
                EventType.HAS_NO_TIME,
                oneDay, oneDay,
                null, null,
                "1 day event"
        );
        EventAddDto dto = EventAddDto.from(request);

        assertThat(
                dto.getStartDateTime().toLocalTime()
        ).isEqualTo(
                LocalTime.of(0, 0, 0)
        );
        assertThat(
                dto.getEndDateTime().toLocalTime()
        ).isEqualTo(
                LocalTime.of(23, 59, 59)
        );
    }
}
