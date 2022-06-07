package com.bicycleManagement.controller.rest;

import com.bicycleManagement.model.Bicycle;
import com.bicycleManagement.model.Customer;
import com.bicycleManagement.model.Rentage;
import com.bicycleManagement.service.BicycleService;
import com.bicycleManagement.service.CustomerService;
import com.bicycleManagement.service.ParkService;
import com.bicycleManagement.service.RentageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private BicycleService bicycleService;

    @Autowired
   private CustomerService customerService;

    @Autowired
    private RentageService rentageService;

    @GetMapping("running-rentages")
     public List<Rentage> findAllRunningRentages(){
         return rentageService.findRunningRentages();
     }

    @GetMapping("mileage-greater-than/{mileage}")
    public List<Bicycle> findByMileageGreaterThan(@PathVariable Integer mileage) {
        return bicycleService.findByMileageGreaterThan(mileage);
    }

     @PostMapping("add-bicycles")
     public ResponseEntity<Bicycle> addBicycle(@RequestBody @Valid Bicycle bicycle){ //Add Bicycle
        return ResponseEntity.created(URI.create("")).body(bicycleService.addBicycle(bicycle));
     }

     @PostMapping("add-customers")
     public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer){
        return ResponseEntity.created(URI.create("")).body(customerService.saveCustomers(customer));
     }

     @DeleteMapping("delete-bicycle{registrationNumber}")
    public void deleteCar(@PathVariable Integer registrationNumber){
        bicycleService.deleteById(registrationNumber);
     }

}
