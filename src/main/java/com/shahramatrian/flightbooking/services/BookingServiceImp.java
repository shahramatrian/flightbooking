package com.shahramatrian.flightbooking.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shahramatrian.flightbooking.model.Booking;
import com.shahramatrian.flightbooking.model.BookingStatus;
import com.shahramatrian.flightbooking.repositories.BookingRepository;

@Service
public class BookingServiceImp implements BookingService{
    BookingRepository bookingRepository;
    Logger logger = LoggerFactory.getLogger(BookingServiceImp.class);
    
    public BookingServiceImp(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> listBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookingRepository.findAll().forEach(bookings::add);
        return bookings;
    }

    @Override
    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void updateBooking(Long id, Booking booking) {
        // TODO         
        Booking bookingFromDB = bookingRepository.findById(id).get();
        logger.debug(bookingFromDB.toString());
        bookingFromDB.setBookingReference(booking.getBookingReference());
        bookingFromDB.setCheckedIn(booking.getCheckedIn());
        bookingFromDB.setOutboundFlight(booking.getOutboundFlight());
        bookingFromDB.setStatus(booking.getStatus());

        bookingRepository.save(bookingFromDB);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public List<Booking> getBookingByStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }
    
}
