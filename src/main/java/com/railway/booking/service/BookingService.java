package com.railway.booking.service;

import com.railway.booking.dto.BookingRequest;
import com.railway.booking.dto.TicketResponse;
import com.railway.booking.exception.BookingClosedException;
import com.railway.booking.exception.InvalidPassengerDetailsException;
import com.railway.booking.exception.InvalidTicketNumberException;
import com.railway.booking.exception.SeatNotAvailableException;
import com.railway.booking.exception.TicketAlreadyCancelledException;
import com.railway.booking.exception.TrainNotFoundException;
import com.railway.booking.model.Booking;
import com.railway.booking.model.BookingStatus;
import com.railway.booking.model.Passenger;
import com.railway.booking.model.Train;
import com.railway.booking.repository.BookingRepository;
import com.railway.booking.repository.PassengerRepository;
import com.railway.booking.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private TrainRepository trainRepository;


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

        if (!train.isActive()) {
            throw new BookingClosedException(
                    "Booking is closed for this train"
            );
        }

        if (train.getAvailableSeats() <= 0) {
            throw new SeatNotAvailableException(
                    "No seats available"
            );
        }

        Integer seatNumber =
                train.getTotalSeats() - train.getAvailableSeats() + 1;

        train.setAvailableSeats(
                train.getAvailableSeats() - 1
        );

        Booking booking = new Booking();

        booking.setPassengerId(passenger.getPassengerId());
        booking.setTrainNumber(train.getTrainNumber());
        booking.setSeatNumber(seatNumber);

        Booking savedBooking =
                bookingRepository.save(booking);

        trainRepository.save(train);

        String ticketNumber =
                String.format("T%05d", savedBooking.getId());

        savedBooking.setTicketNumber(ticketNumber);

        savedBooking =
                bookingRepository.save(savedBooking);

        TicketResponse response = new TicketResponse();

        response.setTicketNumber(savedBooking.getTicketNumber());
        response.setPassengerId(passenger.getPassengerId());
        response.setPassengerName(passenger.getName());
        response.setTrainNumber(train.getTrainNumber());
        response.setTrainName(train.getTrainName());
        response.setSource(train.getSource());
        response.setDestination(train.getDestination());
        response.setSeatNumber(savedBooking.getSeatNumber());
        response.setStatus(savedBooking.getStatus());
        response.setBookingDate(savedBooking.getBookingDate());

        return response;
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

        train.setAvailableSeats(
                train.getAvailableSeats() + 1
        );

        bookingRepository.save(booking);
        trainRepository.save(train);

        Passenger passenger = passengerRepository
                .findByPassengerId(booking.getPassengerId())
                .orElseThrow(() ->
                        new InvalidPassengerDetailsException(
                                "Passenger not found"
                        ));

        TicketResponse response = new TicketResponse();

        response.setTicketNumber(booking.getTicketNumber());
        response.setPassengerId(booking.getPassengerId());
        response.setPassengerName(passenger.getName());
        response.setTrainNumber(train.getTrainNumber());
        response.setTrainName(train.getTrainName());
        response.setSource(train.getSource());
        response.setDestination(train.getDestination());
        response.setSeatNumber(booking.getSeatNumber());
        response.setStatus(booking.getStatus());
        response.setBookingDate(booking.getBookingDate());

        return response;
    }
}