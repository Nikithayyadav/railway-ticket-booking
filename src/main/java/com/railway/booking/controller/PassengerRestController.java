package com.railway.booking.controller;
import com.railway.booking.model.Passenger;
import com.railway.booking.service.PassengerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")

public class PassengerRestController {
    private final PassengerService passengerService;
    public PassengerRestController(PassengerService passengerService) {
        this.passengerService=passengerService;

    }

    @PostMapping
    public Passenger registerPassenger(@RequestBody Passenger passenger) {
        return passengerService.registerPassenger(passenger);
    }


}
