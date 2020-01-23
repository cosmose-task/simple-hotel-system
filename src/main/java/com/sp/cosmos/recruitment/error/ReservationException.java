package com.sp.cosmos.recruitment.error;

public class ReservationException extends RuntimeException {

    public ReservationException() {
        super("Reservation invalid - room not available");
    }
}
