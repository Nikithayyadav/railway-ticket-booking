package com.railway.booking.exception;

import com.railway.booking.response.ApiResponse;
import com.railway.booking.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.railway.booking.exception.BookingClosedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrityViolation(
            DataIntegrityViolationException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                "DATA_INTEGRITY_VIOLATION",
                "The requested data violates a database constraint"
        );

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                null,
                errorResponse,
                null
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.CONFLICT
        );
    }
    @ExceptionHandler(InvalidPassengerDetailsException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidPassengerDetails(
            InvalidPassengerDetailsException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_PASSENGER_DETAILS",
                exception.getMessage()
        );

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                null,
                errorResponse,
                null
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(
            MethodArgumentNotValidException exception) {

        String message = exception.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_PASSENGER_DETAILS",
                message
        );

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                null,
                errorResponse,
                null
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(TrainNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleTrainNotFound(
            TrainNotFoundException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                "TRAIN_NOT_FOUND",
                exception.getMessage()
        );

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                null,
                errorResponse,
                null
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<ApiResponse<Object>> handleSeatNotAvailable(
            SeatNotAvailableException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                "SEAT_NOT_AVAILABLE",
                exception.getMessage()
        );

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                null,
                errorResponse,
                null
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.CONFLICT
        );
    }
    @ExceptionHandler(BookingClosedException.class)
    public ResponseEntity<ApiResponse<Object>> handleBookingClosed(
            BookingClosedException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                "BOOKING_CLOSED",
                exception.getMessage()
        );

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                null,
                errorResponse,
                null
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(InvalidTicketNumberException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidTicketNumber(
            InvalidTicketNumberException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_TICKET_NUMBER",
                exception.getMessage()
        );

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                null,
                errorResponse,
                null
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(TicketAlreadyCancelledException.class)
    public ResponseEntity<ApiResponse<Object>> handleTicketAlreadyCancelled(
            TicketAlreadyCancelledException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                "TICKET_ALREADY_CANCELLED",
                exception.getMessage()
        );

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                null,
                errorResponse,
                null
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.CONFLICT
        );
    }
}