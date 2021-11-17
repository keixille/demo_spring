package com.kangbakso.spring_tutorial.service;

import com.kangbakso.spring_tutorial.dto.CustomerRequestDTO;
import com.kangbakso.spring_tutorial.dto.CustomerResponseDTO;
import com.kangbakso.spring_tutorial.dto.CustomerUpdateRequestDTO;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO getCustomer(long customerId);

    CustomerResponseDTO updateCustomer(long customerId, CustomerUpdateRequestDTO customerUpdateRequestDTO);

    CustomerResponseDTO deleteCustomer(long customerId);
}
