package com.nicolas.EventManager.dto;

import com.nicolas.EventManager.domain.Event;
import com.nicolas.EventManager.domain.EventStatus;
import com.nicolas.EventManager.util.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDto {
    private Long id;

    private String title;

    private String startDate;

    private String endDate;

    private Double price;

    private EventStatus status;

    public EventResponseDto(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.startDate = event.getStartDate().format(DateTimeUtils.DATE_FORMAT);
        this.endDate = event.getEndDate().format(DateTimeUtils.DATE_FORMAT);
        this.price = event.getPrice();
        this.status = event.getStatus();
    }
}
