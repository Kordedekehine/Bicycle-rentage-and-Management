package com.bicycleManagement.repository;

import com.bicycleManagement.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,String> {

    Optional<Admin> findAdminByEmail(String email);
}
