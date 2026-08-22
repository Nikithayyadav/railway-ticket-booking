package com.railway.booking.service;

import com.railway.booking.model.Passenger;
import com.railway.booking.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger registerPassenger(Passenger passenger) {
        passenger.setActive(true);
        passenger.setRegisteredAt(
                LocalDateTime.now(
                        ZoneId.of("Asia/Kolkata")
                )
        );

        Passenger savedPassenger = passengerRepository.save(passenger);

        String generatedPassengerId =
                String.format("P%05d", savedPassenger.getId());

        savedPassenger.setPassengerId(generatedPassengerId);

        return passengerRepository.save(savedPassenger);
    }
}