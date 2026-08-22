package com.railway.booking.controller;

import com.railway.booking.dto.BookingRequest;
import com.railway.booking.dto.TicketResponse;
import com.railway.booking.response.ApiResponse;
import com.railway.booking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ApiResponse<TicketResponse> bookTicket(
            @Valid @RequestBody BookingRequest request) {

        TicketResponse ticketResponse =
                bookingService.bookTicket(request);

        return new ApiResponse<>(
                true,
                ticketResponse,
                null,
                null
        );
    }
    @PutMapping("/{ticketNumber}/cancel")
    public ApiResponse<TicketResponse> cancelTicket(
            @PathVariable String ticketNumber) {

        TicketResponse ticketResponse =
                bookingService.cancelTicket(ticketNumber);

        return new ApiResponse<>(
                true,
                ticketResponse,
                null,
                null
        );
    }
}