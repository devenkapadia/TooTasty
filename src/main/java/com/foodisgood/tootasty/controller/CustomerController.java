package com.foodisgood.tootasty.controller;

import com.foodisgood.tootasty.dto.CustomerRequest;
import com.foodisgood.tootasty.dto.CustomerResponse;
import com.foodisgood.tootasty.dto.LoginRequest;
import com.foodisgood.tootasty.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerResponse> loginCustoemr(@RequestBody @Valid LoginRequest request) {
        CustomerResponse response = customerService.loginCustomer(request.email(), request.password());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getData/{email}")
    public ResponseEntity<CustomerResponse> getData(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.getCustomer(email));
    }

}
