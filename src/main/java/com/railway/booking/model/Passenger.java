package com.railway.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "passenger_id", unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String passengerId;

    @NotBlank(message = "Name is Required")
    private String name;

    @NotNull(message = "Age is Required")
    @Min(value = 1, message = "Age cannot be 0")
    private Integer age;

    @NotNull(message = "Gender is Required")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank(message = "Phone is Required")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email format")
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean active;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime registeredAt;
}