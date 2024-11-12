package com.foodisgood.tootasty.mapper;

import com.foodisgood.tootasty.dto.CustomerRequest;
import com.foodisgood.tootasty.entity.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest.CustomerCreateRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .city(request.city())
                .pincode(request.pincode())
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();
    }

    public Customer toEntity(CustomerRequest.CustomerLoginRequest request) {
        return Customer.builder()
                .email(request.email())
                .password(request.password())
                .build();
    }
}
