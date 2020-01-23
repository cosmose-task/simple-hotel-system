package com.sp.cosmos.recruitment.service;

import com.sp.cosmos.recruitment.model.Customer;
import com.sp.cosmos.recruitment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
