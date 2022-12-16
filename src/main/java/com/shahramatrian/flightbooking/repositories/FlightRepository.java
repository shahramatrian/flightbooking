package com.shahramatrian.flightbooking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shahramatrian.flightbooking.model.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
    // Flight findBySchedule(String arrivalAirportCode, String departureAirportCode, String departureDate);
}
