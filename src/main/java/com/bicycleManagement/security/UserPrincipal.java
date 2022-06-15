package com.bicycleManagement.security;

import com.bicycleManagement.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private Long id;

    private String name;

    private String username;

    private String firstname;

    private String lastname;

    private String phoneNumber;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String firstname, String lastname, String phoneNumber, String username, String email,
                         String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstname = firstname;
        this.phoneNumber = phoneNumber;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }



    public static UserPrincipal create(Customer customer) {
        List<GrantedAuthority> authorities = customer.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getPhoneNumber(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getPassword(),
                authorities
        );
    }

    public Long getId(){
        return id;
    }

    public String getFirstname(){ return firstname;}

    public String getLastname(){ return lastname;}

    public String getPhoneNumber(){ return phoneNumber;}
    public String getEmail(){
        return email;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserPrincipal that = (UserPrincipal) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
