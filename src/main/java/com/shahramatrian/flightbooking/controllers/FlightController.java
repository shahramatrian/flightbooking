package com.shahramatrian.flightbooking.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shahramatrian.flightbooking.model.Flight;
import com.shahramatrian.flightbooking.services.FlightService;

@RestController
@RequestMapping(path="/api/v1/flights", produces={"application/json", "text/xml"})
@CrossOrigin(origins="*")
public class FlightController {
    private static final Logger Log = LoggerFactory.getLogger(FlightController.class);
    FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        try {
            List<Flight> flights = flightService.listFlights();
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } 
        catch (Exception exc) {
            Log.debug("Could not list flights");
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Could not list flights", exc);
        }
    }
    
    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        try {
            Flight newFlight = flightService.createFlight(flight);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("flight", "/api/v1/flight/" + newFlight.getId().toString());
            return new ResponseEntity<>(newFlight, httpHeaders, HttpStatus.CREATED);
        } catch (Exception exc) {
            Log.debug("Could not add flight");
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Could not add flight", exc);
        }
    }

    @PutMapping({"/reserve/{flightId}"})
    public ResponseEntity<String> reserveflight(@PathVariable("flightId") Long flightId) {
        Flight flight = flightService.getFlightById(flightId);
        if (flight.getSeatCapacity() > 0) {
            flight.setSeatCapacity(flight.getSeatCapacity() - 1);
            flightService.updateFlight(flightId, flight);
            int updatedSeatCapacity = flightService.getFlightById(flightId).getSeatCapacity();
            return new ResponseEntity<>("Flight reserved. Seat capacity is: " + updatedSeatCapacity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Flight not reserved. Seat capacity is zero", HttpStatus.OK);
        }
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
