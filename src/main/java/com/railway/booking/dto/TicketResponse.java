package com.railway.booking.dto;

import com.railway.booking.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {

    private String ticketNumber;
    private String passengerId;
    private String passengerName;
    private String trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private Integer seatNumber;
    private BookingStatus status;
    private LocalDateTime bookingDate;
}