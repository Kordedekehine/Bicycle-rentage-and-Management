package com.bicycleManagement.repository;

import com.bicycleManagement.model.Bicycle;
import com.bicycleManagement.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle,Integer> {

    List<Bicycle> findByPark(Park park);

    List<Bicycle> findByProducers(String producer);

    List<Bicycle> findByMileageGreaterThan(Integer mileage);


}
