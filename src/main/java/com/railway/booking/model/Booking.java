package com.railway.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BookingStatus status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime bookingDate;

    public Booking() {
    }

    @PrePersist
    public void beforeSave() {
        if (bookingDate == null) {
            bookingDate = LocalDateTime.now();
        }

        if (status == null) {
            status = BookingStatus.CONFIRMED;
        }
    }
}