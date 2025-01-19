package org.rogarithm.notifyevent.web.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecurEventAddRequestTest {
    @Test
    public void test_field_name_convention() throws JsonProcessingException {
        RecurEventAddRequest request = new RecurEventAddRequest(
                "golf games",
                RecurType.DAY_IN_MONTH,
                new RecurParams("1", "1")
        );
        ObjectMapper objectMapper = new ObjectMapper();
        assertThat(objectMapper.writeValueAsString(request)).isEqualTo(
                "{\"desc\":\"golf games\",\"recurType\":\"DAY_IN_MONTH\",\"recurParams\":{\"dayIdx\":\"1\",\"cnt\":\"1\"}}"
        );
        assertThat(objectMapper.writeValueAsString(request.getRecurParams())).isEqualTo(
                "{\"dayIdx\":\"1\",\"cnt\":\"1\"}"
        );
    }
}
