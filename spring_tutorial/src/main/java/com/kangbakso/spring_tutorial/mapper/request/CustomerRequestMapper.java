package com.kangbakso.spring_tutorial.mapper.request;

import com.kangbakso.spring_tutorial.common.DataMapper;
import com.kangbakso.spring_tutorial.dto.CustomerRequestDTO;
import com.kangbakso.spring_tutorial.entity.postgres.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerRequestMapper extends DataMapper<Customer, CustomerRequestDTO> {
    @Override
    @Mapping(source = "first_name", target = "firstName")
    @Mapping(source = "last_name", target = "lastName")
    Customer convertToEntity(CustomerRequestDTO customerRequestDTO);
}
