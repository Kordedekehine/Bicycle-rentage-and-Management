package com.bicycleManagement.service;

import com.bicycleManagement.model.Bicycle;
import com.bicycleManagement.model.Park;
import com.bicycleManagement.repository.BicycleRepository;
import com.bicycleManagement.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BicycleService {

    @Autowired
    BicycleRepository bicycleRepository;

    @Autowired
    RentageService rentageService;

    @Autowired
    ParkService parkService;

    @Autowired
    Messages messages;

    public List<Bicycle> findByPark(Park park) {
        return bicycleRepository.findByPark(park);
    }

    public List<Bicycle> findByProducer(String producer) {
        return bicycleRepository.findByProducer(producer);
    }


    public Bicycle addBicycle(Bicycle bicycle) {
        if (bicycle.getPark() == null) {
            throw new IllegalStateException("bicycle is not null");
        }
        if (bicycle.getPark().getId() == null || parkService.existById(bicycle.getPark().getId())) {
            throw new EntityNotFoundException(messages.get("Park not found"));
        }
       if (bicycleRepository.existsById(bicycle.getRegistrationNumber())){
           throw new EntityExistsException(messages.get("Car Already Exists"));
       }
       return bicycleRepository.save(bicycle);
    }

    public void deleteById(Integer registrationNumber){
        Bicycle bicycle = bicycleRepository.findById(registrationNumber).
                orElseThrow(() -> new EntityNotFoundException(messages.get("Car not found exception")));
        if (!canDelete(bicycle)){
            throw new IllegalArgumentException(messages.get("Error! Cannot delete car"));
        }
        bicycleRepository.delete(bicycle);
    }

      public boolean canDelete(Bicycle bicycle){
        return bicycle.getPark() != null && rentageService.findByBicycle(bicycle).isEmpty();
      }
}