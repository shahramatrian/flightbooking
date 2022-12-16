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

    @Column
	private String departureAirportCode;

    @Column
	private String departureAirportName;

    @Column
	private String departureCity;

    @Column
	private String departureLocale;

    @Column
	private String arrivalDate;

    @Column
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

	// @OneToOne
    // @MapsId
    // @JoinColumn(name = "booking_id")
    // private Booking booking;
	
    // @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY,
    //         cascade = CascadeType.ALL)
    // private Set<Booking> bookings;


	// @Override
	// public int hashCode() {
	// 	final int prime = 31;
	// 	int result = 1;
	// 	result = prime * result + ((carrierName == null) ? 0 : carrierName.hashCode());
	// 	result = prime * result + ((model == null) ? 0 : model.hashCode());
	// 	result = prime * result + ((no == null) ? 0 : no.hashCode());
	// 	result = prime * result + seatCapacity;
	// 	return result;
	// }

	// @Override
	// public boolean equals(Object obj) {
	// 	if (this == obj)
	// 		return true;
	// 	if (obj == null)
	// 		return false;
	// 	if (getClass() != obj.getClass())
	// 		return false;
	// 	Flight other = (Flight) obj;
	// 	if (carrierName == null) {
	// 		if (other.carrierName != null)
	// 			return false;
	// 	} else if (!carrierName.equals(other.carrierName))
	// 		return false;
	// 	if (model == null) {
	// 		if (other.model != null)
	// 			return false;
	// 	} else if (!model.equals(other.model))
	// 		return false;
	// 	if (no == null) {
	// 		if (other.no != null)
	// 			return false;
	// 	} else if (!no.equals(other.no))
	// 		return false;
	// 	if (seatCapacity != other.seatCapacity)
	// 		return false;
	// 	return true;
	// }
}