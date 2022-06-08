package com.bicycleManagement.repository;

import com.bicycleManagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,String> {

    Optional<Users> findUserByEmail(String email);
    Optional<Users> findUserEntitiesByEmail(String email);
    Optional<Users> findById(int userId);
}
