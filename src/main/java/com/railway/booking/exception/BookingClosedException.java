package com.railway.booking.exception;

public class BookingClosedException extends RuntimeException {

    public BookingClosedException(String message) {
        super(message);
    }
}