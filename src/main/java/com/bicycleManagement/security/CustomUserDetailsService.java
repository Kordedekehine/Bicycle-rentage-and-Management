package com.bicycleManagement.security;

import com.bicycleManagement.model.Customer;
import com.bicycleManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customer = customerRepository.findByUserName(username);
        if (customer.isEmpty()){
            throw new UsernameNotFoundException("Name cannot be found");
        }
        return new CustomUserDetails((Customer) customer);
    }
}
