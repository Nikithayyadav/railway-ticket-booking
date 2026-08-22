package com.railway.booking.repository;

import com.railway.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByTicketNumber(String ticketNumber);
}