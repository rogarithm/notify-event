package org.rogarithm.notifyevent.service.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Test;
import org.rogarithm.notifyevent.web.request.RecurEventAddRequest;
import org.rogarithm.notifyevent.web.request.RecurParams;
import org.rogarithm.notifyevent.web.request.RecurType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RecurEventAddDtoTest {

    @Test
    public void test_field_name_convention() throws JsonProcessingException {
        RecurEventAddRequest request = new RecurEventAddRequest(
                "golf games",
                RecurType.DAY_IN_MONTH,
                new RecurParams("1", "1")
        );

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

        RecurEventAddDto dto = RecurEventAddDto.from(request);
        assertThat(objectMapper.writeValueAsString(dto))
                .isEqualTo(
                        "{\"desc\":\"golf games\",\"recur_type\":\"DAY_IN_MONTH\",\"recur_params\":{\"day_idx\":\"1\",\"cnt\":\"1\"}}"
                );
    }
}
