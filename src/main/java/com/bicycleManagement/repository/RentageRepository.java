package com.bicycleManagement.repository;

import com.bicycleManagement.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentageRepository extends JpaRepository<Bicycle,Integer> {

}
