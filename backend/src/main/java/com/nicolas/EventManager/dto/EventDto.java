package com.nicolas.EventManager.dto;

import com.nicolas.EventManager.domain.EventStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventDto(
        Long id,

        @NotNull(message = "Title is required")
        String title,

        @NotNull(message = "Start date is required")
        LocalDateTime startDate,

        @NotNull(message = "End date is required")
        LocalDateTime endDate,

        @NotNull(message = "Price is required")
        Double price,

        @NotNull(message = "Status is required")
        EventStatus status
) {}