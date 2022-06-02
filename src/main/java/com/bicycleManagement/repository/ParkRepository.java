package com.bicycleManagement.repository;

import com.bicycleManagement.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkRepository extends JpaRepository<Park,Integer> {

}
