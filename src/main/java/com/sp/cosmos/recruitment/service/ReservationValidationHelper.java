package com.sp.cosmos.recruitment.service;

import com.sp.cosmos.recruitment.error.DatePeriodException;

import java.time.LocalDate;

public final class ReservationValidationHelper {

    private ReservationValidationHelper() {}

    public static void validateReservationDate(LocalDate start, LocalDate end) {
        if(start == null || end == null)
            throw new DatePeriodException();
        if (start.isAfter(end)) {
            throw new DatePeriodException();
        }

        if (LocalDate.now().isAfter(start)) {
            throw new DatePeriodException();
        }
    }
}
