package com.kangbakso.spring_tutorial.repository.postgres;

import com.kangbakso.spring_tutorial.entity.postgres.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE customer SET\n" +
            " first_name = :firstName),\n" +
            " last_name = :lastName)\n" +
            " WHERE customer_id = :customerId\n",
            nativeQuery = true)
    void updateCustomer(@Param("customerId") long customerId,
                        @Param("firstName") String firstName,
                        @Param("lastName") String lastName);
}
