package com.foodisgood.tootasty.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private Long pincode;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @Column(name="updated_on")
    private LocalDateTime updatedOn;
}

/*
Created Customers:

{
    "first_name":"Jethalal",
    "last_name":"Gada",
    "email":"jetha@gmail.com",
    "password":"jethiya",
    "city":"Bengaluru",
    "pincode":"590123"
}


 */