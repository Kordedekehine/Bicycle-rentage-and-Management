package com.bicycleManagement.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "Customer Number cannot be empty")
    private Long id;

    @Size(min = 1, max = 255, message = "Name must not be too short or long")
    @NotNull(message = "Name cannot be empty")
    private String lastName;

    @Size(min = 1, max = 255)
    @NotNull(message = "Name cannot be empty")
    private String firstName;


    private String username;


    private String phoneNumber;

    @Email
    private String email;

    private String password;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  //  @JoinColumn
    private Set<Role> role = new HashSet<>();


    public Customer() {
    }

    public Customer(Long id, String lastName, String firstName, String username, String phoneNumber,
                    String email, String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
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

    public Set<Role> getRoles() {
        return role;
    }

    public void setRoles(Set<Role> role) {
        this.role = role;
    }
}
