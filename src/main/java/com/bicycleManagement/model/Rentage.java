package com.bicycleManagement.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    private int kilometre;

    @ManyToOne
    @NotNull
    private Customer driver;

    @ManyToOne
    @NotNull
    private Bicycle bicycle;

    @ManyToOne
    @NotNull
    private Park rentalPark;

    @ManyToOne
    @NotNull
    private Park returnPark;
}
