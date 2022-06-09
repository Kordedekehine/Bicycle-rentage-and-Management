package com.bicycleManagement.repository;

import com.bicycleManagement.model.Bicycle;
import com.bicycleManagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    List<Customer> findByUsername(String username);

    Optional<Bicycle> findByEmail(String email);

    Optional<Bicycle> findByUsernameOrEmail(String username, String email);
}
