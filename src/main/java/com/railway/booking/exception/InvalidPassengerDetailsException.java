package com.railway.booking.exception;

public class InvalidPassengerDetailsException extends RuntimeException {

    public InvalidPassengerDetailsException(String message) {
        super(message);
    }
}