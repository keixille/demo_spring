package com.kangbakso.spring_tutorial.service;

import com.kangbakso.spring_tutorial.dto.request.CustomerRequestDTO;
import com.kangbakso.spring_tutorial.dto.response.CustomerResponseDTO;
import com.kangbakso.spring_tutorial.dto.request.CustomerUpdateRequestDTO;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO getCustomer(long customerId);

    CustomerResponseDTO updateCustomer(long customerId, CustomerUpdateRequestDTO customerUpdateRequestDTO);

    CustomerResponseDTO deleteCustomer(long customerId);
}
