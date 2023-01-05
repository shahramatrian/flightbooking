package com.shahramatrian.flightbooking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shahramatrian.flightbooking.model.Booking;
import com.shahramatrian.flightbooking.model.BookingStatus;
import com.shahramatrian.flightbooking.services.BookingService;


@RestController
@RequestMapping(path="/api/v1/bookings", produces={"application/json", "text/xml"})
public class BookingController {
    private static final Logger Log = LoggerFactory.getLogger(BookingController.class);

    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private ResponseEntity<Booking> updateBookingStatus(Long bookingId, Booking booking) {
        bookingService.updateBooking(bookingId, booking);
        return new ResponseEntity<>(bookingService.getBooking(bookingId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Booking> reserveBooking(@RequestBody Booking booking) {
        booking.setStatus(BookingStatus.UNCONFIRMED);
        try {
            Booking newBooking = bookingService.createBooking(booking);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("booking", "/api/v1/booking/" + newBooking.getId().toString());
            return new ResponseEntity<>(newBooking, httpHeaders, HttpStatus.CREATED);
        } catch (Exception exc) {
            Log.debug("Could not create booking");
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Could not create booking", exc);
        }
    }

    @PutMapping({"/confirm/{bookingId}"})
    public ResponseEntity<Booking> confirmBooking(@PathVariable("bookingId") Long bookingId, @RequestBody Booking booking) {
        if (booking.getStatus() == BookingStatus.UNCONFIRMED) {
            booking.setStatus(BookingStatus.CONFIRMED);
            return updateBookingStatus(bookingId, booking);
        } else {
            Log.debug("Could not confirm booking");
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Could not confirm booking");
        }
    }

    @PutMapping({"/cancel/{bookingId}"})
    public ResponseEntity<Booking> cancelBooking(@PathVariable("bookingId") Long bookingId, @RequestBody Booking booking) {
        if (booking.getStatus() == BookingStatus.CONFIRMED || booking.getStatus() == BookingStatus.UNCONFIRMED) {
            booking.setStatus(BookingStatus.CANCELLED);
            return updateBookingStatus(bookingId, booking);
        } else {
            Log.debug("Could not cancel booking");
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Could not cancel booking");
        }
    }
}
