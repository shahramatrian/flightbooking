package com.shahramatrian.flightbooking.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahramatrian.flightbooking.model.Booking;
import com.shahramatrian.flightbooking.model.BookingStatus;
import com.shahramatrian.flightbooking.services.BookingService;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private ResponseEntity<Booking> UpdateBookingStatus(Long bookingId, Booking booking, BookingStatus status) {
        bookingService.updateBooking(bookingId, booking);
        return new ResponseEntity<>(bookingService.getBooking(bookingId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Booking> reserveBooking(@RequestBody Booking booking) {
        // TODO: check if booking is in correct status
        booking.setStatus(BookingStatus.UNCONFIRMED);
        Booking newBooking = bookingService.createBooking(booking);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("booking", "/api/v1/booking/" + newBooking.getId().toString());
        return new ResponseEntity<>(newBooking, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/confirm/{bookingId}"})
    public ResponseEntity<Booking> confirmBooking(@PathVariable("bookingId") Long bookingId, @RequestBody Booking booking) {
        // TODO: check if booking status is UNCONFIRMED otherwise 
        // throw an exception
        return UpdateBookingStatus(bookingId, booking, BookingStatus.CONFIRMED);
    }

    @PutMapping({"/cancel/{bookingId}"})
    public ResponseEntity<Booking> cancelBooking(@PathVariable("bookingId") Long bookingId, @RequestBody Booking booking) {
        // TODO: check if booking is in correct status
        return UpdateBookingStatus(bookingId, booking, BookingStatus.CANCELLED);
    }
}
