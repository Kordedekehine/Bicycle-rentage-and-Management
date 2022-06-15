package com.bicycleManagement.repository;


import com.bicycleManagement.model.RoleName;
import com.bicycleManagement.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findByName(RoleName name);
}
