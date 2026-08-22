package com.railway.booking.exception;

public class TicketAlreadyCancelledException extends RuntimeException {

    public TicketAlreadyCancelledException(String message) {
        super(message);
    }
}