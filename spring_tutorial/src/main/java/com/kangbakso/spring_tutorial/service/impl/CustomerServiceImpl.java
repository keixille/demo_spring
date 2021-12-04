package com.kangbakso.spring_tutorial.service.impl;

import com.kangbakso.spring_tutorial.dto.request.CustomerRequestDTO;
import com.kangbakso.spring_tutorial.dto.response.CustomerResponseDTO;
import com.kangbakso.spring_tutorial.dto.request.CustomerUpdateRequestDTO;
import com.kangbakso.spring_tutorial.entity.postgres.Customer;
import com.kangbakso.spring_tutorial.mapper.request.CustomerRequestMapper;
import com.kangbakso.spring_tutorial.mapper.response.CustomerResponseMapper;
import com.kangbakso.spring_tutorial.producer.CustomerProducer;
import com.kangbakso.spring_tutorial.repository.postgres.CustomerRepository;
import com.kangbakso.spring_tutorial.service.CustomerService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    private final CustomerRequestMapper customerRequestMapper;
    private final CustomerResponseMapper customerResponseMapper;
    private CustomerProducer customerProducer;

    public CustomerServiceImpl(CustomerRequestMapper customerRequestMapper,
                               CustomerResponseMapper customerResponseMapper,
                               CustomerProducer customerProducer) {
        this.customerRequestMapper = customerRequestMapper;
        this.customerResponseMapper = customerResponseMapper;
        this.customerProducer = customerProducer;
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        logger.info("Start create customer");

        Customer customer = customerRequestMapper.convertToEntity(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);

        customerProducer.produce("create-customer", savedCustomer.toString());
        return customerResponseMapper.convertToDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO getCustomer(long customerId) {
        logger.info("Start get customer");

        Customer retrievedCustomer = customerRepository.findById(customerId).get();
        return customerResponseMapper.convertToDTO(retrievedCustomer);
    }

    @Override
    public CustomerResponseDTO updateCustomer(long customerId, CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        logger.info("Start update customer");

        customerRepository.updateCustomer(
                customerId,
                customerUpdateRequestDTO.getFirst_name(),
                customerUpdateRequestDTO.getLast_name());

        Optional<Customer> optionalUpdatedCustomer = customerRepository.findById(customerId);
        Customer updatedCustomer = null;
        if (optionalUpdatedCustomer.isPresent()) {
            updatedCustomer = optionalUpdatedCustomer.get();
        }
        return customerResponseMapper.convertToDTO(updatedCustomer);
    }

    @Override
    public CustomerResponseDTO deleteCustomer(long customerId) {
        logger.info("Start delete customer");

        Customer deletedCustomer = customerRepository.findById(customerId).get();
        customerRepository.deleteById(customerId);
        return customerResponseMapper.convertToDTO(deletedCustomer);
    }
}
