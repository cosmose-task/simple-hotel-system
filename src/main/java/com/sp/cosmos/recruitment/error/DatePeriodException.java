package com.sp.cosmos.recruitment.error;

public class DatePeriodException extends RuntimeException {

    public DatePeriodException() {
    }

    public DatePeriodException(String message) {
        super("Reservation dates invalid");
    }
}
