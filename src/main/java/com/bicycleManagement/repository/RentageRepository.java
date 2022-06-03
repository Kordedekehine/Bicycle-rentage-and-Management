package com.bicycleManagement.repository;

import com.bicycleManagement.model.Bicycle;
import com.bicycleManagement.model.Rentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentageRepository extends JpaRepository<Rentage,Integer> {

     @Query("SELECT r FROM Rentage r WHERE r.kilometre IS NULL AND r.returnDate IS NULL AND r.returnPark IS NULL")
     List<Rentage> findRunningRentage();

     List<Rentage> findByBicycle(Bicycle bicycle);
}
