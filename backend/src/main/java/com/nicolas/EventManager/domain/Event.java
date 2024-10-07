package com.nicolas.EventManager.domain;

import com.nicolas.EventManager.dtos.EventDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name="events")
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Double price;

    private EventStatus status;

    public Event (EventDto data) {
        this.title = data.title();
        this.startDate = data.getStartDate();
        this.endDate = data.getEndDate();
        this.price = data.price();
        this.status = data.status();
    }
}
