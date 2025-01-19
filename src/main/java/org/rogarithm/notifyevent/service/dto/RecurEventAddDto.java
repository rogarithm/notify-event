package org.rogarithm.notifyevent.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.rogarithm.notifyevent.web.request.RecurEventAddRequest;
import org.rogarithm.notifyevent.web.request.RecurParams;
import org.rogarithm.notifyevent.web.request.RecurType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecurEventAddDto {
    private static final Logger log = LoggerFactory.getLogger(RecurEventAddDto.class);

    /*
    RecurEventAddRequest 타입 객체를 RecurEventAddDto로 바꾸고, 이 dto 객체를
    webClient로 외부 API 요청으로 넘길 때 필드명은 모두 snake case가 되어야 한다
     */
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("recur_type")
    private RecurType recurType;
    @JsonProperty("recur_params")
    private RecurParams recurParams;

    public RecurEventAddDto(String desc, RecurType recurType, RecurParams recurParams) {
        this.desc = desc;
        this.recurType = recurType;
        this.recurParams = recurParams;
    }

    public String getDesc() {
        return desc;
    }

    public RecurType getRecurType() {
        return recurType;
    }

    public RecurParams getRecurParams() {
        return recurParams;
    }

    public static RecurEventAddDto from(RecurEventAddRequest request) {
        ObjectMapper snakeCaseMapper = new ObjectMapper();
        snakeCaseMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());

        RecurParams recurParamsInSnakeCase = snakeCaseMapper.convertValue(request.getRecurParams(), RecurParams.class);
        RecurEventAddDto dto = new RecurEventAddDto(
                request.getDesc(),
                request.getRecurType(),
                recurParamsInSnakeCase
        );

        return dto;
    }
}
