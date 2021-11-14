package com.kangbakso.spring_tutorial.controller;

import com.kangbakso.spring_tutorial.data.postgres.Customer;
import com.kangbakso.spring_tutorial.repository.postgres.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostgresController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/postgres/create")
    public String createCustomer() {
        Customer customer = new Customer("Test", "User");
        customerRepository.save(customer);
        return customer.toString();
    }

    @GetMapping("/postgres/get")
    public String getCustomer(@RequestParam long id) {
        Optional<Customer> retrievedCustomer = customerRepository.findById(id);
        return retrievedCustomer.toString();
    }
}
