package com.railway.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String ticketNumber;

    @Column(nullable = false)
    private String passengerId;

    @Column(nullable = false)
    private String trainNumber;

    @Column(nullable = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BookingStatus status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime bookingDate;
}