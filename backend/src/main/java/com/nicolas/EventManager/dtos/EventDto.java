package com.nicolas.EventManager.dtos;

import com.nicolas.EventManager.domain.EventStatus;
import com.nicolas.EventManager.util.DateTimeUtils;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDateTime;

public record EventDto(
        Long id,

        @NotNull(message = "Title is required")
        String title,

        @NotNull(message = "Start date is required")
        @Getter(AccessLevel.NONE)
        String startDate,

        @NotNull(message = "End date is required")
        @Getter(AccessLevel.NONE)
        String endDate,

        @NotNull(message = "Price is required")
        Double price,

        @NotNull(message = "Status is required")
        EventStatus status
) {

    public LocalDateTime getStartDate() {
        return LocalDateTime.parse(startDate, DateTimeUtils.DATE_FORMAT);
    }

    public LocalDateTime getEndDate() {
        return LocalDateTime.parse(endDate, DateTimeUtils.DATE_FORMAT);
    }
}