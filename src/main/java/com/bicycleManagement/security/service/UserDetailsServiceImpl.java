package com.bicycleManagement.security.service;

import com.bicycleManagement.model.Customer;
import com.bicycleManagement.repository.CustomerRepository;
import com.bicycleManagement.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUserNameOrEmail(usernameOrEmail).orElseThrow( () -> new UsernameNotFoundException("User not found with username: " + usernameOrEmail));
        return UserDetailsImpl.build(customer);
    }
}
