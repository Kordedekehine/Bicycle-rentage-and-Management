package com.bicycleManagement.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "rentage")
public class Rentage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @FutureOrPresent
    @NotNull(message = "Date of rentals")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;

    @NotNull(message = "Date of return")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    private Integer kilometre;

    @ManyToOne
    @NotNull
    private Customer rider;

    @ManyToOne
    @NotNull
    private Bicycle bicycle;

    @ManyToOne
    @NotNull
    private Park rentalPark;

    @ManyToOne
    @NotNull
    private Park returnPark;

    public Rentage() {
    }

    public Rentage(Integer id, LocalDate rentalDate, LocalDate returnDate, Integer kilometre,
                   Customer rider, Bicycle bicycle, Park rentalPark, Park returnPark) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.kilometre = kilometre;
        this.rider = rider;
        this.bicycle = bicycle;
        this.rentalPark = rentalPark;
        this.returnPark = returnPark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getKilometre() {
        return kilometre;
    }

    public void setKilometre(Integer kilometre) {
        this.kilometre = kilometre;
    }

    public Customer getRider() {
        return rider;
    }

    public void setRider(Customer driver) {
        this.rider = driver;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }

    public Park getRentalPark() {
        return rentalPark;
    }

    public void setRentalPark(Park rentalPark) {
        this.rentalPark = rentalPark;
    }

    public Park getReturnPark() {
        return returnPark;
    }

    public void setReturnPark(Park returnPark) {
        this.returnPark = returnPark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rentage rentage = (Rentage) o;
        return kilometre == rentage.kilometre && Objects.equals(id, rentage.id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rentalDate, returnDate, kilometre, rider, bicycle, rentalPark, returnPark);
    }
}
