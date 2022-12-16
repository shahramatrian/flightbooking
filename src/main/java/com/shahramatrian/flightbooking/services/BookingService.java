package com.shahramatrian.flightbooking.services;

import java.util.List;

import com.shahramatrian.flightbooking.model.Booking;
import com.shahramatrian.flightbooking.model.BookingStatus;

public interface BookingService {
    List<Booking> listBookings();

    Booking getBooking(Long id);

    Booking createBooking(Booking booking);

    void updateBooking(Long id, Booking booking);

    void deleteBooking(Long bookingId);

    List<Booking> getBookingByStatus(BookingStatus status);
}
