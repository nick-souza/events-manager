package com.nicolas.EventManager.dto;

import com.nicolas.EventManager.domain.EventStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record EventDto(
        Long id,

        @NotNull(message = "Name is required")
        String name,

        @NotNull(message = "Start date is required")
        @FutureOrPresent(message = "Start date must be in the present or future")
        LocalDateTime startDate,

        @NotNull(message = "End date is required")
        @FutureOrPresent(message = "End date must be in the present or future")
        LocalDateTime endDate,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        Double price,

        @NotNull(message = "Status is required")
        EventStatus status
) {}