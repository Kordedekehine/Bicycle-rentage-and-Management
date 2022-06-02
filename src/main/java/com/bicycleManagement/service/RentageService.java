package com.bicycleManagement.service;

import com.bicycleManagement.model.Bicycle;
import com.bicycleManagement.model.Rentage;
import com.bicycleManagement.repository.RentageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RentageService {

    @Autowired
    RentageRepository rentageRepository;

    @Autowired
    BicycleService bicycleService;


    public List<Rentage> findByBicycle(Bicycle bicycle) {

        return rentageRepository.findByBicycle(bicycle);
    }

}
