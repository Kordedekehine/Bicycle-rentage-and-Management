package com.bicycleManagement.security.servicesecurity;

import com.bicycleManagement.model.Customer;
import com.bicycleManagement.repository.CustomerRepository;
import com.bicycleManagement.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.websocket.server.ServerEndpoint;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow( () -> new UsernameNotFoundException("User not found with username: " + usernameOrEmail));
        return UserDetailsImpl.build(customer);
    }
}
