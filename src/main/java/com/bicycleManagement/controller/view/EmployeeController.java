package com.bicycleManagement.controller.view;

import com.bicycleManagement.bean.RentageFinishBean;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

     @PostMapping("add-rentage")
    public String refreshAddRentageForm(@ModelAttribute ("rentage") Rentage rentage,
                                       RedirectAttributes redirectAttributes){
       redirectAttributes.addFlashAttribute("rentage",rentage);
       return "redirect:/employee/add-rentage"; //"redirect:/employee/create-rental
    }

    //@PostMapping("/create-rental/process")
    @PostMapping("/add-rental/process")
   public ModelAndView processAddRentageForm(@Valid @ModelAttribute ("rentage") Rentage rentage,
                                             BindingResult bindingResult){
   ModelAndView createRentageForm = new ModelAndView("frontends/add-rentage");
   List<Park> parks = parkService.findAll();
   createRentageForm.addObject("bicycle",bicycleService.findByPark(parks.get(0)));
   createRentageForm.addObject("park",parks);
   createRentageForm.addObject("customers",customerService.findAllCustomers());

   /*[ BindingResult ] is Spring's object that holds the result of the validation and binding and contains
   *errors that may have occurred. The BindingResult must come right after the model object that is
   *validated or else Spring will fail to validate the object and throw an exception.
    */
   if (bindingResult.hasErrors()){
       return createRentageForm;
   }

   if (!rentageService.canRentOut(rentage)){
       return createRentageForm.addObject("Bicycle Mismatch Error",messages
               .get("Bicycle Mismatch Error"));
   }
    rentageService.addBeforeRentDetails(rentage);
   return new ModelAndView("redirect:/employee/running-rentages")
           .addObject("success",messages.get("add rental success"));
   }

   @GetMapping("/running-rentages")
    public String showRunningRentalsForm(Model model, @ModelAttribute ("error") String error,
                                         @ModelAttribute("success") String success){
   model.addAttribute("rentages",rentageService.findRunningRentages());
   model.addAttribute("error",error);
   model.addAttribute("success",success);
   return "frontends/running-rentals";
   }

  @GetMapping("/finish-rentage/{id}")
    public ModelAndView showFinishForm(@PathVariable ("id") Integer id){
      Optional<Rentage> optional = rentageService.existsAndCanFinish(id);
      if (optional.isEmpty()){
          return new ModelAndView("redirect:/employee/running-rentals")
                  .addObject("error",messages.get("rentage not found"));
      }
      return new ModelAndView("frontend/finish-rentage")
              .addObject("parks",parkService.findAll())
              .addObject("finishRentageBean", RentageFinishBean.fromRentage(optional.get()));
      //convert rentage to class in rentagefinishbean class
  }
}
