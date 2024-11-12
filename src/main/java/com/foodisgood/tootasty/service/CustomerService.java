package com.foodisgood.tootasty.service;

import com.foodisgood.tootasty.dto.CustomerRequest;
import com.foodisgood.tootasty.entity.Customer;
import com.foodisgood.tootasty.helper.EncryptionService;
import com.foodisgood.tootasty.helper.JWTHelper;
import com.foodisgood.tootasty.mapper.CustomerMapper;
import com.foodisgood.tootasty.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final EncryptionService encryptionService;
    private final JWTHelper jwHelper;
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public Customer createCustomer(CustomerRequest.CustomerCreateRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customer = repo.save(customer);
        return customer;
    }

    public String loginCustomer(CustomerRequest.CustomerLoginRequest request) {
        Optional<Customer> optionalCustomer = repo.findByEmail(request.email());

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (encryptionService.validates(request.password(), customer.getPassword())) {
                return jwHelper.generateToken(customer.getEmail());
            } else {
                return "Invalid password";
            }
        } else {
            return "User not found";
        }
    }

    public Customer fetchCustomer(String email) {
        return repo.findByEmail(email).orElse(null); // Return null if not found
    }

    public Customer updateCustomer(String email, CustomerRequest.CustomerUpdateRequest updateRequest) {
        // Fetch the existing customer by email
        Optional<Customer> optionalCustomer = repo.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            // Update fields only if they are present in the request
            if (updateRequest.firstName() != null) {
                customer.setFirstName(updateRequest.firstName());
            }
            if (updateRequest.lastName() != null) {
                customer.setLastName(updateRequest.lastName());
            }
            if (updateRequest.city() != null) {
                customer.setCity(updateRequest.city());
            }
            if (updateRequest.pincode() != null) {
                customer.setPincode(updateRequest.pincode());
            }
            customer.setUpdatedOn(LocalDateTime.now());
            return repo.save(customer);
        } else {
            return null;
        }
    }

}