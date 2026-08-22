package com.railway.booking.controller;

import com.railway.booking.dto.BookingRequest;
import com.railway.booking.dto.TicketResponse;
import com.railway.booking.response.ApiResponse;
import com.railway.booking.response.PageMeta;
import com.railway.booking.service.BookingService;
import com.railway.booking.service.TicketPdfService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingRestController {

    private final BookingService bookingService;
    private final TicketPdfService ticketPdfService;


    // Book Ticket
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


    // Cancel Ticket
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


    // View Ticket
    @GetMapping("/{ticketNumber}")
    public ApiResponse<TicketResponse> viewTicket(
            @PathVariable String ticketNumber) {

        TicketResponse ticketResponse =
                bookingService.viewTicket(ticketNumber);

        return new ApiResponse<>(
                true,
                ticketResponse,
                null,
                null
        );
    }


    // Booking History
    @GetMapping("/history/{passengerId}")
    public ApiResponse<List<TicketResponse>> bookingHistory(
            @PathVariable String passengerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<TicketResponse> history =
                bookingService.bookingHistory(
                        passengerId,
                        pageable
                );

        PageMeta pageMeta = new PageMeta(
                history.getNumber(),
                history.getSize(),
                history.getTotalElements(),
                history.getTotalPages(),
                history.isFirst(),
                history.isLast()
        );

        return new ApiResponse<>(
                true,
                history.getContent(),
                null,
                pageMeta
        );
    }
    @GetMapping("/{ticketNumber}/print")
    public ResponseEntity<byte[]> printTicket(
            @PathVariable String ticketNumber) {

        byte[] pdf =
                ticketPdfService.generateTicketPdf(
                        ticketNumber
                );

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=ticket-"
                                + ticketNumber
                                + ".pdf"
                )
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .body(pdf);
    }
}