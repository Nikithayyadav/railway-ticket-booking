package com.railway.booking.repository;

import com.railway.booking.model.Booking;
import com.railway.booking.model.BookingStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    Optional<Booking> findByTicketNumber(
            String ticketNumber
    );

    Page<Booking> findByPassengerId(
            String passengerId,
            Pageable pageable
    );

    List<Booking> findByTrainNumberAndStatusOrderByBookingDateAsc(
            String trainNumber,
            BookingStatus status
    );

    long countByTrainNumberAndStatus(
            String trainNumber,
            BookingStatus status
    );
}