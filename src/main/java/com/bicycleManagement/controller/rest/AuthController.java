package com.bicycleManagement.controller.rest;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import com.bicycleManagement.model.Customer;
import com.bicycleManagement.model.Role;
import com.bicycleManagement.model.RoleName;
import com.bicycleManagement.payload.ApiResponse;
import com.bicycleManagement.payload.JwtResponse;
import com.bicycleManagement.payload.LoginRequest;
import com.bicycleManagement.payload.SignUpRequest;
import com.bicycleManagement.repository.CustomerRepository;
import com.bicycleManagement.repository.RoleRepository;
import com.bicycleManagement.security.UserDetailsImpl;
import com.bicycleManagement.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getPassword(),
                 roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (customerRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseEntity(new ApiResponse(false, "Error: Username is already taken!"),
                            HttpStatus.BAD_REQUEST));
        }
        if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseEntity(new ApiResponse(false, "Error: Email is already in use!"),
                            HttpStatus.BAD_REQUEST));
        }
        //CUSTOMER HAVE TO CREATE A NEW CUSTOMER ACCOUNT
        Customer customer = new Customer(
                signUpRequest.getId(),
                signUpRequest.getLastName(),
                signUpRequest.getFirstName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> stringRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (stringRoles.isEmpty()) {
            Role customerRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role is not found"));
            roles.add(customerRole);
        } else {
            stringRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Role is not found"));
                        roles.add(adminRole);
                        break;
                    case "employee":
                        Role empRole = roleRepository.findByName(RoleName.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RuntimeException("Role is not found"));
                        roles.add(empRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
            customer.setRoles(roles);
            customerRepository.save(customer);
            return ResponseEntity.ok(new ApiResponse(true, "Customer successfully saved"));
    }

}
