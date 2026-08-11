    package com.railway.booking.model;

    import jakarta.persistence.*;
    import java.time.LocalDateTime;
    import com.fasterxml.jackson.annotation.JsonProperty;

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

        private String name;
        private Integer age;
       @Enumerated(EnumType.STRING)
        private Gender gender;

       private String phone;
       private String email;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
       private boolean active = true;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private LocalDateTime registeredAt;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Passenger() {
       }

        public String getPassengerId() {
            return passengerId;
        }

        public Integer getAge() {
            return age;
        }

        public LocalDateTime getRegisteredAt() {
            return registeredAt;
        }

        public void setRegisteredAt(LocalDateTime registeredAt) {
            this.registeredAt = registeredAt;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPassengerId(String passengerId) {
            this.passengerId = passengerId;
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

