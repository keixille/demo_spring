package com.kangbakso.spring_tutorial.repository.postgres;

import com.kangbakso.spring_tutorial.data.postgres.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
