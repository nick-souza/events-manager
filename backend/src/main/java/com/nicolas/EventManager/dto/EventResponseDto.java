package com.nicolas.EventManager.dto;

import com.nicolas.EventManager.domain.Event;
import com.nicolas.EventManager.domain.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDto {
    private Long id;

    private String title;

    private String description;

    private String startDate;

    private String endDate;

    private Double price;

    private EventStatus status;

    public EventResponseDto(Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.US);

        this.id = event.getId();
        this.title = event.getTitle();
        this.startDate = event.getStartDate().format(formatter);
        this.endDate = event.getEndDate().format(formatter);
        this.price = event.getPrice();
        this.status = event.getStatus();
    }
}
