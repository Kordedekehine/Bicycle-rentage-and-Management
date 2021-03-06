package com.bicycleManagement.repository;

import com.bicycleManagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUsername(String username);


    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<Customer> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);
}
