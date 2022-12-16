package com.shahramatrian.flightbooking.controllers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahramatrian.flightbooking.model.Flight;
import com.shahramatrian.flightbooking.services.FlightService;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightController {
    FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.listFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight newFlight = flightService.createFlight(flight);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("flight", "/api/v1/flight/" + newFlight.getId().toString());
        return new ResponseEntity<>(newFlight, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/reserve/{flightId}"})
    public ResponseEntity<Flight> reserveflight(@PathVariable("flightId") Long flightId) {
        //TODO: cases where seat capacity is zero must be considered
        Flight flight = flightService.getFlightById(flightId);
        flight.setSeatCapacity(flight.getSeatCapacity() - 1);
        flightService.updateFlight(flightId, flight);
        return new ResponseEntity<>(flightService.getFlightById(flightId), HttpStatus.OK);
    }

    @PutMapping({"/release/{flightId}"})
    public ResponseEntity<Flight> releaseflight(@PathVariable("flightId") Long flightId) {
        //TODO: exception handling where seat capacity is already full must be added
        Flight flight = flightService.getFlightById(flightId);
        flight.setSeatCapacity(flight.getSeatCapacity() + 1);
        flightService.updateFlight(flightId, flight);
        return new ResponseEntity<>(flightService.getFlightById(flightId), HttpStatus.OK);
    }
}
