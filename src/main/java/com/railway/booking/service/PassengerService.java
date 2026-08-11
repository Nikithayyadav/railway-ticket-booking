package com.railway.booking.service;

import com.railway.booking.model.Passenger;
import com.railway.booking.repository.PassengerRepository;
import org.springframework.stereotype.Service;


@Service

public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger registerPassenger(Passenger passenger) {
        passenger.setActive(true);

        Passenger savedPassenger = passengerRepository.save(passenger);
        String generatedPassengerId = String.format("P%05d", savedPassenger.getId());
        savedPassenger.setPassengerId(generatedPassengerId);
        return passengerRepository.save(savedPassenger);


    }


}