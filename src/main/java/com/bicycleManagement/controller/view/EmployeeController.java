package com.bicycleManagement.controller.view;

import com.bicycleManagement.model.Bicycle;
import com.bicycleManagement.model.Park;
import com.bicycleManagement.model.Rentage;
import com.bicycleManagement.service.BicycleService;
import com.bicycleManagement.service.CustomerService;
import com.bicycleManagement.service.ParkService;
import com.bicycleManagement.service.RentageService;
import com.bicycleManagement.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("employee")
@SessionAttributes({"RentageFinishBean"})
public class EmployeeController {

    @Autowired
    private Messages messages;

    @Autowired
    private ParkService parkService;

    @Autowired
    private BicycleService bicycleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RentageService rentageService;

    @PostMapping("add-re")
     public String showAddRentageForm(Model model, @ModelAttribute("rentage")Rentage rentage){
         List<Park> parks = parkService.findAll();
         List<Bicycle> bicycles;

         if (parks.isEmpty()){
             bicycles = new ArrayList<>();
         }else {
            bicycles = bicycleService.findByPark(rentage.getRentalPark() == null ?
                    parks.get(0): rentage.getRentalPark());
         }

         rentage.setRentalDate(LocalDate.now());
         model.addAttribute("rentages", rentage);
         model.addAttribute("bicycles",bicycles);
         model.addAttribute("parks",parks);
         model.addAttribute("customers",customerService.findAllCustomers());

         return "frontends/add-rentage"; //"fragments/create-rental" //THE FILE
     }

     @PostMapping()
    public String refreshAddRentageForm(@ModelAttribute ("rentage") Rentage rentage,
                                       RedirectAttributes redirectAttributes){
       redirectAttributes.addFlashAttribute("rentage",rentage);
       return "redirect:/employee/add-rentage"; //"redirect:/employee/create-rental
    }

   public ModelAndView processAddRentageForm(@Valid @ModelAttribute ("rentage") Rentage rentage,
                                             BindingResult bindingResult){
   ModelAndView createRentageForm = new ModelAndView("frontends/add-rentage");
   List<Park> parks = parkService.findAll();
   }
}
