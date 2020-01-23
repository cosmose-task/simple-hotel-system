package com.sp.cosmos.recruitment.controller;

import com.sp.cosmos.recruitment.model.Customer;
import com.sp.cosmos.recruitment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity createCustomer(@Valid @RequestBody Customer customer,
                                         UriComponentsBuilder uriComponentsBuilder) {
        UriComponents uriComponents =
                uriComponentsBuilder.path("/customer/{id}")
                        .buildAndExpand(customerService.createCustomer(customer).getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

}
