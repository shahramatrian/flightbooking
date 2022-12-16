package com.shahramatrian.flightbooking.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shahramatrian.flightbooking.model.Booking;
import com.shahramatrian.flightbooking.model.BookingStatus;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByStatus(BookingStatus status);
}
