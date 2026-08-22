    package com.railway.booking.model;

    import jakarta.persistence.*;
    import java.time.LocalDateTime;
    import lombok.Data;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.Min;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;


    @Data
    @Entity
    @Table(name="passengers")


    public class Passenger {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private Long id;

        @Column(name="passenger_id",unique = true)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private String passengerId;
        @NotBlank(message="Name is Required")
        private String name;

        @NotNull(message="Age is Required")
        @Min(value=1,message = "Age cannot be 0")
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
       private boolean active = true;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private LocalDateTime registeredAt;


        public Passenger() {
       }



        public Passenger(String name,
                         Integer age,
                         Gender gender,
                         String phone,
                         String email) {
               this.name = name;
               this.age = age;
               this.gender = gender;
               this.phone = phone;
               this.email = email;

           }

           @PrePersist
                   public void beforeSave() {
            if (registeredAt==null) {
                registeredAt=LocalDateTime.now();
            }
           }
       }

