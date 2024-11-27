package org.rogarithm.notifyevent.service.dto;

import org.rogarithm.notifyevent.web.request.EventAddRequest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class EventAddDto {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;

    public EventAddDto(LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getDescription() {
        return description;
    }

    public static EventAddDto from(EventAddRequest request) {
        LocalTime startTime = request.getStartTime() != null ? request.getStartTime() : LocalTime.of(0, 0, 0);
        LocalDateTime startDateTime = LocalDateTime.of(request.getStartDate(), startTime);

        // 왜 LocalTime.of(23, 59, 59)로 연산하나?
        //  H2 DB의 LocalDateTime precision이 nano second까지 커버하지 않는다
        //  그래서 LocalTime.MAX로 설정할 경우, endDate로 설정된 날의 다음날로 DB에 저장된다
        //  이벤트 종료 시간 설정에 nano second의 정확성이 필요 없다고 판단해 이렇게 연산하도록 구현했다
        LocalTime endTime = request.getEndTime() != null ? request.getEndTime() : LocalTime.of(23, 59, 59);
        LocalDateTime endDateTime = LocalDateTime.of(request.getEndDate(), endTime);

        return new EventAddDto(startDateTime, endDateTime, request.getDescription());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventAddDto that = (EventAddDto) o;
        return Objects.equals(startDateTime, that.startDateTime) && Objects.equals(endDateTime, that.endDateTime) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime, description);
    }
}
