package com.bicycleManagement.service;

import com.bicycleManagement.model.Customer;
import com.bicycleManagement.repository.CustomerRepository;
import com.bicycleManagement.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
   private Messages messages;

    @Autowired
    private CustomerRepository customerRepository;

     public List<Customer> findAllCustomers(){
         return customerRepository.findAll();
     }

    public Customer saveCustomers(Customer customer){
         if (customerRepository.existsById(customer.getId())){
           throw new EntityExistsException(messages.get("Customer already exists"));
         }
        return customerRepository.save(customer);
    }

    private boolean existsById(Long id){
         return customerRepository.existsById(id);
    }
}
