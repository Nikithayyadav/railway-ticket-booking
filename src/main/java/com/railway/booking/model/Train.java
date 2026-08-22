package com.railway.booking.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "trains")


public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String trainNumber;

    @Column(nullable = false)
    private String trainName;

    @Column(nullable = false)
    private String source;

    @Column (nullable = false)
    private String destination;

    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer availableSeats;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean active=true;

public Train() {

}
public Train(String trainNumber,
             String trainName,
             String source,
             String destination,
             Integer totalSeats) {
    this.trainNumber=trainNumber;
    this.trainName=trainName;
    this.source=source;
    this.destination=destination;
    this.totalSeats=totalSeats;
    this.availableSeats=totalSeats;
    this.active=true;
}


}
