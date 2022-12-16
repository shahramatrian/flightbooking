package com.shahramatrian.flightbooking.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shahramatrian.flightbooking.model.Flight;
import com.shahramatrian.flightbooking.repositories.FlightRepository;

@Component
public class FlightLoader implements CommandLineRunner {
    public final FlightRepository flightRepository;

    public FlightLoader(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadFlights();
    }

    private void loadFlights() {
        if (flightRepository.count() == 0) {
            flightRepository.save(
                Flight.builder()
                    .departureDate("2023-01-11T17:00+0000")
                    .departureAirportCode("LGW")
                    .departureAirportName("London Gatwick")
                    .departureCity("London")
                    .departureLocale("Europe/London")
                    .arrivalDate("2023-01-11T19:15+0000")
                    .arrivalAirportCode("MAD")
                    .arrivalAirportName("Madrid Barajas")
                    .arrivalCity("Madrid")
                    .arrivalLocale("Europe/Madrid")
                    .ticketPrice(500)
                    .ticketCurrency("EUR")
                    .flightNumber(1815)
                    .seatCapacity(4)
                    .build()
            );
            flightRepository.save(
                Flight.builder()
                    .departureDate("2023-01-11T12:00+0000")
                    .departureAirportCode("LGW")
                    .departureAirportName("London Gatwick")
                    .departureCity("London")
                    .departureLocale("Europe/London")
                    .arrivalDate("2023-01-11T14:15+0000")
                    .arrivalAirportCode("MAD")
                    .arrivalAirportName("Madrid Barajas")
                    .arrivalCity("Madrid")
                    .arrivalLocale("Europe/Madrid")
                    .ticketPrice(1000)
                    .ticketCurrency("EUR")
                    .flightNumber(1830)
                    .seatCapacity(10)
                    .build()
            );
            flightRepository.save(
                Flight.builder()
                    .departureDate("2023-01-10T12:00+0000")
                    .departureAirportCode("LGW")
                    .departureAirportName("London Gatwick")
                    .departureCity("London")
                    .departureLocale("Europe/London")
                    .arrivalDate("2023-01-10T14:15+0000")
                    .arrivalAirportCode("MAD")
                    .arrivalAirportName("Madrid Barajas")
                    .arrivalCity("Madrid")
                    .arrivalLocale("Europe/Madrid")
                    .ticketPrice(500)
                    .ticketCurrency("EUR")
                    .flightNumber(1820)
                    .seatCapacity(5)
                    .build()
            );
            flightRepository.save(
                Flight.builder()
                    .departureDate("2023-01-10T17:00+0000")
                    .departureAirportCode("LGW")
                    .departureAirportName("London Gatwick")
                    .departureCity("London")
                    .departureLocale("Europe/London")
                    .arrivalDate("2023-01-10T19:15+0000")
                    .arrivalAirportCode("MAD")
                    .arrivalAirportName("Madrid Barajas")
                    .arrivalCity("Madrid")
                    .arrivalLocale("Europe/Madrid")
                    .ticketPrice(1000)
                    .ticketCurrency("EUR")
                    .flightNumber(1820)
                    .seatCapacity(10)
                    .build()
            );
            System.out.println("Sample Flights Loaded");
        }
    }
}
