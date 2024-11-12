package com.foodisgood.tootasty.controller;

import com.foodisgood.tootasty.dto.CustomerRequest;
import com.foodisgood.tootasty.entity.Customer;
import com.foodisgood.tootasty.helper.AuthHelper;
import com.foodisgood.tootasty.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final AuthHelper authHelper;

    @PostMapping("/register")
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerRequest.CustomerCreateRequest customerCreateRequest) {
        return ResponseEntity.ok(customerService.createCustomer(customerCreateRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody @Valid CustomerRequest.CustomerLoginRequest customerLoginRequest) {
        return ResponseEntity.ok(customerService.loginCustomer(customerLoginRequest));
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchCustomer(HttpServletRequest request) {
        String email = authHelper.extractAndCheckJWT(request);
        if (email == null) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid or expired token");
        }
        Customer customer = customerService.fetchCustomer(email);
        return ResponseEntity.ok(customer);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerRequest.CustomerUpdateRequest updateRequest, HttpServletRequest request) {
        String email = authHelper.extractAndCheckJWT(request);
        if (email == null) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid or expired token"); // Custom error response
        }

        Customer updatedCustomer = customerService.updateCustomer(email, updateRequest);

        if (updatedCustomer == null) {
            return ResponseEntity.status(404).body("User not found!");
        }
        return ResponseEntity.ok(updatedCustomer);
    }
}

/*
{
    "first_name":"Jethalal",
    "last_name":"Gada",
    "email":"jetha@gmail.com",
    "password":"jethiya"
}
*/
