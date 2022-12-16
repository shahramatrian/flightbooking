package com.shahramatrian.flightbooking.services;

import java.util.List;

import com.shahramatrian.flightbooking.model.Flight;

public interface FlightService {
    List<Flight> listFlights();

    Flight getFlightById(Long id);

    Flight createFlight(Flight flight);

    void updateFlight(Long id, Flight flight);

    void deleteFlight(Long flightId);
}
