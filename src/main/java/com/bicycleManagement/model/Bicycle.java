package com.bicycleManagement.model;


import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "bicycles")
public class Bicycle {

    @Id
    @NotBlank(message = "Reg Number cannot be empty")
    private String registrationNumber;

    @NotNull(message = "Registration year cannot be empty")
    @Range(min = 2017, max = 2050)
    private Integer registrationYear;

    @NotNull(message = "Production year cannot be empty")
    private Integer productionYear;

    @NotBlank(message = "Model cannot be empty" )
    private String model;

    @NotBlank(message = "Producers  cannot be empty")
    private String producers;

    @NotBlank(message = "Park cannot be empty")
    private String park;

    public Bicycle() {
    }

    public Bicycle(String registrationNumber, Integer registrationYear,
                   Integer productionYear, String model, String producers, String park) {
        this.registrationNumber = registrationNumber;
        this.registrationYear = registrationYear;
        this.productionYear = productionYear;
        this.model = model;
        this.producers = producers;
        this.park = park;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(Integer registrationYear) {
        this.registrationYear = registrationYear;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicycle bicycle = (Bicycle) o;
        return Objects.equals(registrationNumber, bicycle.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, registrationYear, productionYear, model, producers, park);
    }
}
