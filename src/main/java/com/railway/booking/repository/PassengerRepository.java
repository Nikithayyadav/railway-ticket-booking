package com.railway.booking.repository;

import  com.railway.booking.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository; 


public interface PassengerRepository
    extends JpaRepository<Passenger, Long>{ 

}
