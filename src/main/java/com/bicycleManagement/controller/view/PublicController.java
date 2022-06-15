package com.bicycleManagement.controller.view;

import com.bicycleManagement.bean.SelectedParkBean;
import com.bicycleManagement.model.Bicycle;
import com.bicycleManagement.service.BicycleService;
import com.bicycleManagement.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/customer")
public class PublicController {

    @Autowired
    private BicycleService bicycleService;

    @Autowired
    private ParkService parkService;

    @GetMapping
    public String showForm(Model model){
        model.addAttribute("selectedParkBean",new SelectedParkBean());
        model.addAttribute("parks",parkService.findAll());
        return "frontends/search-rentages";
    }

    public String processForm(Model model, @Valid @ModelAttribute ("selectedParkBean") SelectedParkBean
                              selectedParkBean, BindingResult bindingResult) {
        model.addAttribute("parks",parkService.findAll());
        model.addAttribute("bicycles", bindingResult.hasErrors() ? null : bicycleService
                .findByPark(selectedParkBean.getPark()));
        return "frontends/search-rentages";
    }
}
