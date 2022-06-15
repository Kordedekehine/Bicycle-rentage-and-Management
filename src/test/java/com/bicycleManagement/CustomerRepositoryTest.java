package com.bicycleManagement;

import com.bicycleManagement.model.Customer;
import com.bicycleManagement.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testCreateUser(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        Customer customer = new Customer("korex","dsfhu@gmail.com",encodedPassword);
//        customer.setFirstName("korede");
//        customer.setLastName("salami");
//        customer.setId(1L);
//        customer.setEmail("dsfhu@gmail.com");
//        customer.setPassword(encodedPassword);
//        customer.setUsername("korex");
//        customer.setPhoneNumber("08088675804");
//        customer.setRoles(roles);


    Customer customer1 =  customerRepository.save(customer);

        Assertions.assertThat(customer1).isNotNull();
        Assertions.assertThat(customer1.getId()).isGreaterThan(0);
    }
}
