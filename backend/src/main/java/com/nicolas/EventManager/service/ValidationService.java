package com.nicolas.EventManager.service;

import com.nicolas.EventManager.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidationService {

    public void validateDates(LocalDateTime startDate, LocalDateTime endDate) throws BadRequestException {
        LocalDateTime now = LocalDateTime.now();

        if (endDate.isBefore(now)) {
            throw new BadRequestException("End date must be in the future");
        }

        if (startDate.isAfter(endDate)) {
            throw new BadRequestException("Start date must be before end date");
        }
    }

    public void validatePrice(Double price) throws BadRequestException {
        if (price < 0) {
            throw new BadRequestException("Price must be greater than 0");
        }
    }
}
