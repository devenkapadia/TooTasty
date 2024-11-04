package com.foodisgood.tootasty.service;

import com.foodisgood.tootasty.dto.CustomerRequest;
import com.foodisgood.tootasty.dto.CustomerResponse;
import com.foodisgood.tootasty.entity.Customer;
import com.foodisgood.tootasty.helper.EncryptionService;
import com.foodisgood.tootasty.mapper.CustomerMapper;
import com.foodisgood.tootasty.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encryptPassword(customer.getPassword()));
        repo.save(customer);
        return "Customer Created Successfully";
    }

    public CustomerResponse getCustomer(String emailId) {
        return mapper.toCustomerResponse(getByEmail(emailId));
    }

    public String loginCustomer(String emailId, String password) {
        Customer customer = getByEmail(emailId);

//        if (customer == null || !customer.getPassword().equals(password)) {
//            throw new RuntimeException("Invalid email or password");
//        }

        if(!encryptionService.verifyPassword(password, customer.getPassword())) {
            return "Wrong Credentials";
        }
        return "User logged in Successfully";
    }

    public Customer getByEmail(String emailId) {
        return repo.findByEmail(emailId).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}