package com.shahramatrian.flightbooking.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shahramatrian.flightbooking.model.Flight;
import com.shahramatrian.flightbooking.repositories.FlightRepository;

@Service
public class FlightServiceImp implements FlightService{
    FlightRepository flightRepository;
    Logger logger = LoggerFactory.getLogger(FlightServiceImp.class);
    
    public FlightServiceImp(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> listFlights() {
        List<Flight> flights = new ArrayList<>();
        flightRepository.findAll().forEach(flights::add);
        return flights;
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseGet(null);
    }

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void updateFlight(Long id, Flight flight) {
        // TODO
        Flight flightFromDB = flightRepository.findById(id).get();
        logger.debug(flightFromDB.toString());
        flightFromDB.setFlightNumber(flight.getFlightNumber());
        flightFromDB.setArrivalAirportCode(flight.getArrivalAirportCode());
        flightFromDB.setArrivalAirportName(flight.getArrivalAirportName());
        flightFromDB.setArrivalCity(flight.getArrivalCity());
        flightFromDB.setArrivalDate(flight.getArrivalDate());
        flightFromDB.setArrivalLocale(flight.getArrivalLocale());
        flightFromDB.setDepartureAirportCode(flight.getDepartureAirportCode());
        flightFromDB.setDepartureAirportName(flight.getDepartureAirportName());
        flightFromDB.setDepartureCity(flight.getDepartureCity());
        flightFromDB.setDepartureDate(flight.getDepartureDate());
        flightFromDB.setDepartureLocale(flight.getDepartureLocale());
        flightFromDB.setSeatCapacity(flight.getSeatCapacity());
        flightFromDB.setTicketCurrency(flight.getTicketCurrency());
        flightFromDB.setTicketPrice(flight.getTicketPrice());
        flightRepository.save(flightFromDB);
        
    }

    @Override
    public void deleteFlight(Long flightId) {
        flightRepository.deleteById(flightId); 
    }
}
