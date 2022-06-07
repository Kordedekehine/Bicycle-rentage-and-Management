package com.bicycleManagement.service;

import com.bicycleManagement.bean.RentageFinishBean;
import com.bicycleManagement.model.Bicycle;
import com.bicycleManagement.model.Rentage;
import com.bicycleManagement.repository.RentageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentageService {

    @Autowired
   private RentageRepository rentageRepository;

    @Autowired
    BicycleService bicycleService;


    public List<Rentage> findByBicycle(Bicycle bicycle) {

        return rentageRepository.findByBicycle(bicycle);
    }

    public void addBeforeRentDetails(Rentage rentage){
         rentage.setId(null);
         rentage.setKilometre(null);
         rentage.setReturnDate(null);
         rentage.setReturnPark(null);
         rentage.getBicycle().setPark(null);

         rentageRepository.save(rentage);
    }

    public void addAfterServiceDetails(Rentage rentage, RentageFinishBean rentageFinishBean){
        rentage.setReturnPark(rentageFinishBean.getReturnPark());//get the time it returns to pack
        rentage.setKilometre(rentageFinishBean.getKilometre());
        rentage.getBicycle().setPark(rentage.getReturnPark());
        rentage.getBicycle().setMileage(rentage.getBicycle().getMileage() + rentage.getKilometre());

        rentageRepository.save(rentage);
    }

//Find all currently running rentages
       public List<Rentage> findRunningRentages(){
        return rentageRepository.findAll();
       }

  //search for bicycle with the same producers
    public List<Rentage> findByProducer(Bicycle bicycle){
        return rentageRepository.findByBicycle(bicycle);
    }

     public Optional<Rentage> existsAndCanFinish(Integer id){
       if (id == null){
           return Optional.empty();
       }
     Optional<Rentage> check = rentageRepository.findById(id);
       Rentage rentage;
       if (check.isPresent() && canFinish(rentage = check.get())){
           return Optional.of(rentage);
       }
       return Optional.empty();
     }

   //NOTE: A value is present only if we have created Optional with a non-null value.

   // Checks whether a given rental can be finished
          //   Returns true if return date, km and return station fields are null, and false otherwise.
     public boolean canFinish(Rentage rentage){
        return rentage.getRentalPark() == null && rentage.getKilometre() == null &&
                rentage.getReturnPark() == null;
     }

     // Check if a particular bicycle was parked in a particular park
    //Checks whether a rental can be created ot not. A rental can be created,
     // if the provided car is indeed a car of the provided station.
     // This method is used for security reasons, since users can manipulate the frontend values via F12.

     public boolean canRentOut(Rentage rentage){
        return bicycleService.findByPark(rentage.getRentalPark()).contains(rentage.getBicycle());
     }

     // we have to check if the date of return is same as the date on record. If not, return false
    //If true, then we set the date to the return date, and we record true
    public boolean verifyReturnDate(Rentage rentage, RentageFinishBean rentageFinishBean){
      if (rentage.getRentalDate().isAfter(rentageFinishBean.getReturnDate())){
          return false;
      }else {
           rentage.setReturnDate(rentageFinishBean.getReturnDate());
           return true;
      }
    }
}
