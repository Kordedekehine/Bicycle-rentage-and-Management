package com.bicycleManagement.model;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.Id;

public class Employee {

    @Id
    private String id;
    @Column
    private String email;
    @Enumerated
    private Roles roles;
    @Column
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
