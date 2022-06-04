package com.bicycleManagement.bean;

import com.bicycleManagement.model.Park;

import javax.validation.constraints.NotNull;

public class SelectedParkBean {

    @NotNull
    private Park park;

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }
}
