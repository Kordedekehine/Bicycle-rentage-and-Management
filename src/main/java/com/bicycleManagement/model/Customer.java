package com.bicycleManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Size(min = 1, max = 255, message = "{nameRange}")
    @NotNull(message = "Name cannot be empty")
    private String firstName;

    public Customer() {
    }

    public Customer(Integer customerNumber, String lastName, String firstName) {
        this.customerNumber = customerNumber;
        this.lastName = lastName;
        this.firstName = firstName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerNumber, customer.customerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber, lastName, firstName);
    }
}
