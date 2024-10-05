package com.nicolas.EventManager.dto;

import com.nicolas.EventManager.domain.EventStatus;

import java.time.LocalDateTime;

public record EventDto(Long id, String name, LocalDateTime startDate, LocalDateTime endDate, Double price, EventStatus status) {
}