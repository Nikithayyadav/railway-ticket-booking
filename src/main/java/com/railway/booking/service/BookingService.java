package com.railway.booking.service;

import com.railway.booking.dto.BookingRequest;
import com.railway.booking.dto.TicketResponse;
import com.railway.booking.exception.BookingClosedException;
import com.railway.booking.exception.InvalidPassengerDetailsException;
import com.railway.booking.exception.InvalidTicketNumberException;
import com.railway.booking.exception.TicketAlreadyCancelledException;
import com.railway.booking.exception.TrainNotFoundException;
import com.railway.booking.model.Booking;
import com.railway.booking.model.BookingStatus;
import com.railway.booking.model.Passenger;
import com.railway.booking.model.Train;
import com.railway.booking.repository.BookingRepository;
import com.railway.booking.repository.PassengerRepository;
import com.railway.booking.repository.TrainRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final TrainRepository trainRepository;


    // Book Ticket
    public TicketResponse bookTicket(BookingRequest request) {

        Passenger passenger = passengerRepository
                .findByPassengerId(request.getPassengerId())
                .orElseThrow(() ->
                        new InvalidPassengerDetailsException(
                                "Passenger not found"
                        ));

        Train train = trainRepository
                .findByTrainNumber(request.getTrainNumber())
                .orElseThrow(() ->
                        new TrainNotFoundException(
                                "Train not found"
                        ));

        if (!train.isActive() || train.isStarted()) {
            throw new BookingClosedException(
                    "Booking is closed because the train is inactive or has already started"
            );
        }

        Booking booking = new Booking();

        booking.setPassengerId(
                passenger.getPassengerId()
        );

        booking.setTrainNumber(
                train.getTrainNumber()
        );

        booking.setBookingDate(
                LocalDateTime.now(
                        ZoneId.of("Asia/Kolkata")
                )
        );

        // Confirm booking if seats are available
        if (train.getAvailableSeats() > 0) {

            Integer seatNumber =
                    train.getTotalSeats()
                            - train.getAvailableSeats()
                            + 1;

            train.setAvailableSeats(
                    train.getAvailableSeats() - 1
            );

            booking.setSeatNumber(seatNumber);
            booking.setStatus(BookingStatus.CONFIRMED);

        } else {

            // Add passenger to waiting list
            booking.setStatus(BookingStatus.WAITING);
            booking.setSeatNumber(null);
        }

        Booking savedBooking =
                bookingRepository.save(booking);

        trainRepository.save(train);

        String ticketNumber =
                String.format(
                        "T%05d",
                        savedBooking.getId()
                );

        savedBooking.setTicketNumber(ticketNumber);

        savedBooking =
                bookingRepository.save(savedBooking);

        return buildTicketResponse(savedBooking);
    }


    // Cancel Ticket
    public TicketResponse cancelTicket(String ticketNumber) {

        Booking booking = bookingRepository
                .findByTicketNumber(ticketNumber)
                .orElseThrow(() ->
                        new InvalidTicketNumberException(
                                "Invalid ticket number"
                        ));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new TicketAlreadyCancelledException(
                    "Ticket is already cancelled"
            );
        }

        Train train = trainRepository
                .findByTrainNumber(booking.getTrainNumber())
                .orElseThrow(() ->
                        new TrainNotFoundException(
                                "Train not found"
                        ));

        booking.setStatus(BookingStatus.CANCELLED);

        bookingRepository.save(booking);

        /*
         * If the cancelled ticket was confirmed,
         * its seat can be given to a waiting passenger.
         */
        if (booking.getSeatNumber() != null) {

            train.setAvailableSeats(
                    train.getAvailableSeats() + 1
            );

            promoteWaitingPassenger(train);
        }

        trainRepository.save(train);

        return buildTicketResponse(booking);
    }


    // Promote First Waiting Passenger
    private void promoteWaitingPassenger(Train train) {

        List<Booking> waitingBookings =
                bookingRepository
                        .findByTrainNumberAndStatusOrderByBookingDateAsc(
                                train.getTrainNumber(),
                                BookingStatus.WAITING
                        );

        if (waitingBookings.isEmpty()) {
            return;
        }

        Booking waitingBooking =
                waitingBookings.get(0);

        Integer seatNumber =
                train.getTotalSeats()
                        - train.getAvailableSeats();

        waitingBooking.setSeatNumber(seatNumber);

        waitingBooking.setStatus(
                BookingStatus.CONFIRMED
        );

        train.setAvailableSeats(
                train.getAvailableSeats() - 1
        );

        bookingRepository.save(waitingBooking);
    }


    // View Ticket
    public TicketResponse viewTicket(String ticketNumber) {

        Booking booking = bookingRepository
                .findByTicketNumber(ticketNumber)
                .orElseThrow(() ->
                        new InvalidTicketNumberException(
                                "Invalid ticket number"
                        ));

        return buildTicketResponse(booking);
    }


    // Booking History
    public Page<TicketResponse> bookingHistory(
            String passengerId,
            Pageable pageable) {

        Passenger passenger = passengerRepository
                .findByPassengerId(passengerId)
                .orElseThrow(() ->
                        new InvalidPassengerDetailsException(
                                "Passenger not found"
                        ));

        return bookingRepository
                .findByPassengerId(
                        passengerId,
                        pageable
                )
                .map(this::buildTicketResponse);
    }


    // Build Ticket Response
    private TicketResponse buildTicketResponse(
            Booking booking) {

        Passenger passenger = passengerRepository
                .findByPassengerId(
                        booking.getPassengerId()
                )
                .orElseThrow(() ->
                        new InvalidPassengerDetailsException(
                                "Passenger not found"
                        ));

        Train train = trainRepository
                .findByTrainNumber(
                        booking.getTrainNumber()
                )
                .orElseThrow(() ->
                        new TrainNotFoundException(
                                "Train not found"
                        ));

        return new TicketResponse(
                booking.getTicketNumber(),
                passenger.getPassengerId(),
                passenger.getName(),
                train.getTrainNumber(),
                train.getTrainName(),
                train.getSource(),
                train.getDestination(),
                booking.getSeatNumber(),
                booking.getStatus(),
                booking.getBookingDate()
        );
    }
}