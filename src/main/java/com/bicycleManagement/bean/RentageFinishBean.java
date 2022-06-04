package com.bicycleManagement.bean;

import com.bicycleManagement.model.Bicycle;
import com.bicycleManagement.model.Customer;
import com.bicycleManagement.model.Park;
import com.bicycleManagement.model.Rentage;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class RentageFinishBean {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;
    private Customer rider;
    private Bicycle bicycle;
    private Park rentalPark;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Return date cannot be null")
    private LocalDate returnDate;

    @NotNull
    private Park returnPark;

    @NotNull
    @Range(min = 0,max = 100_000_000)
    private Integer kilometre;


    public RentageFinishBean() {
    }

    public RentageFinishBean(Integer id, LocalDate rentalDate, Customer rider, Bicycle bicycle,
                             Park rentalPark, LocalDate returnDate, Park returnPark, Integer kilometre) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.rider = rider;
        this.bicycle = bicycle;
        this.rentalPark = rentalPark;
        this.returnDate = returnDate;
        this.returnPark = returnPark;
        this.kilometre = kilometre;
    }

//    rental The rental to be turned into a FinishRentalBean
//      return converted rental as FinishRentalBean

    public static RentageFinishBean fromRentage(Rentage rentage) {
        return new RentageFinishBean(
                rentage.getId(),
                rentage.getRentalDate(),
                rentage.getRider(),
                rentage.getBicycle(),
                rentage.getRentalPark(),
                LocalDate.now(),
                null,
                0
        );
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

    public Customer getDriver() {
        return rider;
    }

    public void setDriver(Customer rider) {
        this.rider = rider;
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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Park getReturnPark() {
        return returnPark;
    }

    public void setReturnPark(Park returnPark) {
        this.returnPark = returnPark;
    }

    public Integer getKilometre() {
        return kilometre;
    }

    public void setKilometre(Integer kilometre) {
        this.kilometre = kilometre;
    }
}
