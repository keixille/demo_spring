package com.kangbakso.spring_tutorial.mapper.response;

import com.kangbakso.spring_tutorial.common.DataMapper;
import com.kangbakso.spring_tutorial.dto.CustomerResponseDTO;
import com.kangbakso.spring_tutorial.entity.postgres.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerResponseMapper extends DataMapper<Customer, CustomerResponseDTO> {
    @Override
    @Mapping(source = "id", target = "customer_id")
    @Mapping(source = "firstName", target = "first_name")
    @Mapping(source = "lastName", target = "last_name")
    CustomerResponseDTO convertToDTO(Customer customer);
}
