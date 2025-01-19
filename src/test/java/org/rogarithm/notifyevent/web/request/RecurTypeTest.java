package org.rogarithm.notifyevent.web.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecurTypeTest {

    @DisplayName("문자열 형태의 반복 일정 타입을 enum 타입으로 변환할 수 있다")
    @Test
    public void convert_string_to_recur_type_enum() {
        String type = "DAY_IN_MONTH";
        RecurType recurType = RecurType.of(type);
        assertThat(recurType).isEqualTo(RecurType.DAY_IN_MONTH);
    }

    @DisplayName("정의되지 않은 반복 일정 문자열일 경우 오류를 반환한다")
    @Test
    public void throw_exception_when_convert_nonexisting_recur_type_string() {
        String type = "X";
        Assertions.assertThatThrownBy(() -> RecurType.of(type))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
