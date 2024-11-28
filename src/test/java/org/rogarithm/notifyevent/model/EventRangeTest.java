package org.rogarithm.notifyevent.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @DisplayName("주어진 날짜가 현재 EventRange의 날짜 범위 안에 있는지 확인할 수 있다")
    @Test
    public void test_given_date_is_in_range() {
        EventRange eventRange = new EventRange(
                LocalDateTime.of(LocalDate.of(2024, 11, 24), LocalTime.of(0,0,0)),
                LocalDateTime.of(LocalDate.of(2024, 11, 26), LocalTime.of(23,59,59))
        );

        LocalDate dateInsideRange = LocalDate.of(2024, 11, 25);
        assertThat(eventRange.includes(dateInsideRange)).isTrue();

        LocalDate dateBeforeRange = LocalDate.of(2024, 11, 20);
        assertThat(eventRange.includes(dateBeforeRange)).isFalse();

        LocalDate dateAfterRange = LocalDate.of(2024, 11, 27);
        assertThat(eventRange.includes(dateAfterRange)).isFalse();
    }

    @DisplayName("검색 일자 바깥 이벤트를 제외한다")
    @Test
    public void test_given_date_is_in_range_outside_date() {
        LocalDate date = LocalDate.of(2024, 11, 25);

        EventRange eventRangeOutsideDate = new EventRange(
                LocalDateTime.of(LocalDate.of(2024, 11, 26), LocalTime.of(20,0)),
                LocalDateTime.of(LocalDate.of(2024, 11, 26), LocalTime.of(21,0))
        );
        assertThat(eventRangeOutsideDate.includes(date)).isFalse();
    }

    @DisplayName("검색 일자 내의 이벤트를 포함한다")
    @Test
    public void test_given_date_is_in_range_part_in_date() {
        LocalDate date = LocalDate.of(2024, 11, 25);

        EventRange eventRangePartInDate = new EventRange(
                LocalDateTime.of(LocalDate.of(2024, 11, 24), LocalTime.of(20,0)),
                LocalDateTime.of(LocalDate.of(2024, 11, 25), LocalTime.of(21,0))
        );
        assertThat(eventRangePartInDate.includes(date)).isTrue();
    }

    @DisplayName("검색 일자와 일치하는 이벤트를 포함한다")
    @Test
    public void test_given_date_is_in_range_same_as_date() {
        LocalDate date = LocalDate.of(2024, 11, 25);

        EventRange eventRangeSameAsDate = new EventRange(
                LocalDateTime.of(LocalDate.of(2024, 11, 25), LocalTime.of(0,0,0)),
                LocalDateTime.of(LocalDate.of(2024, 11, 25), LocalTime.of(23,59,59))
        );
        assertThat(eventRangeSameAsDate.includes(date)).isTrue();
    }

    @DisplayName("검색 일자 내 시간대 일부를 갖는 이벤트를 포함한다")
    @Test
    public void test_given_date_is_in_range_inside_date() {
        LocalDate date = LocalDate.of(2024, 11, 25);

        EventRange eventRangeInsideDate = new EventRange(
                LocalDateTime.of(LocalDate.of(2024, 11, 25), LocalTime.of(20,0)),
                LocalDateTime.of(LocalDate.of(2024, 11, 25), LocalTime.of(21,0))
        );
        assertThat(eventRangeInsideDate.includes(date)).isTrue();
    }
}
