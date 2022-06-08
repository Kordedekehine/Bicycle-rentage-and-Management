package com.bicycleManagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @NotBlank(message = "Customer Number cannot be empty")
    private Integer customerNumber;

    @Size(min = 1, max = 255, message = "Name must not be too short or long")
    @NotNull(message = "Name cannot be empty")
    private String lastName;

    @Size(min = 1, max = 255)
    @NotNull(message = "Name cannot be empty")
    private String firstName;

    @Column
    private String username;

    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private String password;
    @Enumerated
    private Roles roles;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn
    private List<Bicycle> vehicleList=new ArrayList<>();
    @Column
    private Boolean locked=false;
    @Column
    private Boolean enabled=false;
    @Column
    private String validationToken;

    @Column
    private String resetPasswordToken;


    public Customer(Integer customerNumber, String lastName, String firstName, String username, String phoneNumber,
                    String email, String password, Roles roles, List<Bicycle> vehicleList,
                    Boolean locked, Boolean enabled, String validationToken, String resetPasswordToken) {
        this.customerNumber = customerNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.vehicleList = vehicleList;
        this.locked = locked;
        this.enabled = enabled;
        this.validationToken = validationToken;
        this.resetPasswordToken = resetPasswordToken;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public List<Bicycle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Bicycle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getValidationToken() {
        return validationToken;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }
}
