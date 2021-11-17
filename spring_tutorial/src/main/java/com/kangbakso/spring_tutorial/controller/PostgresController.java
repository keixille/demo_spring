package com.kangbakso.spring_tutorial.controller;

import com.kangbakso.spring_tutorial.dto.CustomerRequestDTO;
import com.kangbakso.spring_tutorial.dto.CustomerResponseDTO;
import com.kangbakso.spring_tutorial.dto.CustomerUpdateRequestDTO;
import com.kangbakso.spring_tutorial.dto.ErrorResponseDTO;
import com.kangbakso.spring_tutorial.repository.postgres.CustomerRepository;
import com.kangbakso.spring_tutorial.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostgresController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @PostMapping("/postgres/customer")
    public ResponseEntity postCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        HttpHeaders responseHeaders = new HttpHeaders();
        CustomerResponseDTO customerResponseDTO = customerService.createCustomer(customerRequestDTO);

        return new ResponseEntity<>(customerResponseDTO, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/postgres/customer/{customer_id}")
    public ResponseEntity getCustomer(@PathVariable("customer_id") long customer_id) {
        HttpHeaders responseHeaders = new HttpHeaders();

        try {
            CustomerResponseDTO customerResponseDTO = customerService.getCustomer(customer_id);
            return new ResponseEntity<>(customerResponseDTO, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO("Customer not exist"), responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/postgres/customer/{customer_id}")
    public ResponseEntity putCustomer(@PathVariable("customer_id") long customer_id, @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        HttpHeaders responseHeaders = new HttpHeaders();

        try {
            CustomerResponseDTO customerResponseDTO = customerService.updateCustomer(customer_id, customerUpdateRequestDTO);
            return new ResponseEntity<>(customerResponseDTO, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO("Customer not exist"), responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/postgres/customer/{customer_id}")
    public ResponseEntity deleteCustomer(@PathVariable("customer_id") long customer_id) {
        HttpHeaders responseHeaders = new HttpHeaders();

        try {
            CustomerResponseDTO customerResponseDTO = customerService.deleteCustomer(customer_id);
            return new ResponseEntity<>(customerResponseDTO, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO("Customer not exist"), responseHeaders, HttpStatus.NOT_FOUND);
        }
    }
}
