package com.shahramatrian.flightbooking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
//import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class Booking {
	@Id
	@GeneratedValue
    @Column(updatable = false, nullable = false)
	private Long id;

	@Column
	private BookingStatus status;

	// @Column
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight outboundFlight;

    @Column
	private String paymentToken;
    
	@Column
	private Boolean checkedIn;

	@Column
	private String customer;
    
	@Column
	private String createdAt;
    
	@Column
	private String bookingReference;

}