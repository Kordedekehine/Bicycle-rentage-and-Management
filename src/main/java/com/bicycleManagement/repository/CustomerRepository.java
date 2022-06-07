package com.bicycleManagement.repository;

import com.bicycleManagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findByUsername(String username);
}
