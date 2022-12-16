package com.shahramatrian.flightbooking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Flight {

	@Id
	@GeneratedValue
    @Column(updatable = false, nullable = false)
	private Long id;

    @Column
	private String departureDate;

    @Column(length = 3)
	private String departureAirportCode;

    @Column
	private String departureAirportName;

    @Column
	private String departureCity;

    @Column
	private String departureLocale;

    @Column
	private String arrivalDate;

    @Column(length = 3)
	private String arrivalAirportCode;

    @Column
	private String arrivalAirportName;

    @Column
	private String arrivalCity;

    @Column
	private String arrivalLocale;

    @Column
	private int ticketPrice;

    @Column
	private String ticketCurrency;

    @Column
	private int flightNumber;

    @Column
	private int seatCapacity;
}