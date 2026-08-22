package com.railway.booking.controller;

import com.railway.booking.model.Passenger;
import com.railway.booking.service.PassengerService;
import com.railway.booking.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
public class PassengerRestController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping
    public ApiResponse<Passenger> registerPassenger(
           @Valid @RequestBody Passenger passenger) {

        Passenger savedPassenger =
                passengerService.registerPassenger(passenger);

        return new ApiResponse<>(
                true,
                savedPassenger,
                null,
                null
        );
    }
}