package com.railway.booking.exception;

public class InvalidTicketNumberException extends RuntimeException {

    public InvalidTicketNumberException(String message) {
        super(message);
    }
}