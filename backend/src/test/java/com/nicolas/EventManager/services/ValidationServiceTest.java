package com.nicolas.EventManager.services;

import com.nicolas.EventManager.exception.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();

    @Test
    @DisplayName("Should not throw an exception when the dates are valid")
    public void testValidateDates_ValidDates() {
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(2);
        validationService.validateDates(startDate, endDate);
    }

    @Test
    @DisplayName("Should throw an exception when the end date is before the start date")
    public void testValidateDates_EndDateBeforeStartDate() {
        LocalDateTime startDate = LocalDateTime.now().plusDays(2);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        assertThrows(BadRequestException.class, () -> validationService.validateDates(startDate, endDate));
    }

//    @Test
//    @DisplayName("Should throw an exception when the end date is in the past")
//    public void testValidateDates_EndDateInPast() {
//        LocalDateTime startDate = LocalDateTime.now().minusDays(2);
//        LocalDateTime endDate = LocalDateTime.now().minusDays(1);
//        assertThrows(BadRequestException.class, () -> validationService.validateDates(startDate, endDate));
//    }

    @Test
    @DisplayName("Should not throw an exception when the price is valid")
    public void testValidatePrice_ValidPrice() {
        validationService.validatePrice(100.0);
    }

    @Test
    @DisplayName("Should throw an exception when the price is negative")
    public void testValidatePrice_NegativePrice() {
        assertThrows(BadRequestException.class, () -> validationService.validatePrice(-100.0));
    }
}
