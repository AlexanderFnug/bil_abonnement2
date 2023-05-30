package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.*;
import com.example.bilabonnement.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import java.util.List;

/**
 * General explanation that applies to all controllers:
 *      There are some repeating methods in all controllers e.g. carForm, employeeDashboard etc.,
 *      all that these methods handle is navigating the user to the appropriate html page, possibly
 *      with the parameter 'model' which lets us use the spring Model object to store data
 *      that thymeleaf can then access. Add-/Delete-/Edit user/car/etc. use WebRequest to
 *      get the neceassary information from the html fields and then performs necessary CRUD operation
 *      with the 'add-' methods creating a new object through '@ModelAttribute <Model> <Name>', and
 *      using setters to set the correct values gotten from WebRequest.
 */

@Controller
public class CarController {
    @Autowired
    Service service;

    @GetMapping("/carDashboard")
    public String carDashboard(Model model){
        List<Car> carList = service.fetchAllCars();
        List<CarModel> carModelList = service.fetchAllCarModels();
        model.addAttribute("carList", carList);
        model.addAttribute("carModelList", carModelList);
        return "cardashboard.html";
    }

    @GetMapping("/carForm")
    public String carForm(Model model){
        List<CarModel> carModelList = service.fetchAllCarModels();
        model.addAttribute("carModelList",carModelList);
        return "carform.html";
    }

    @GetMapping("/carModelForm")
    public String carModelForm(Model model){
        List<String> fuelTypeList = service.fetchAllFuelTypes();
        model.addAttribute("fuelTypeList",fuelTypeList);
        return "carmodelform.html";
    }

    @PostMapping("/addCar")
    public String addCar(@ModelAttribute Car car, WebRequest wr){
        car.setModelID(Integer.parseInt(wr.getParameter("carmodel")));
        car.setYear(Integer.parseInt(wr.getParameter("year")));
        car.setMileage(Integer.parseInt(wr.getParameter("mileage")));
        car.setStatusID(1); //New cars added are expected to be in 'FINE' condition, which is what '1' references.
        service.addCar(car);
        return "redirect:/carDashboard";
    }

    @PostMapping("/addCarModel")
    public String addCarModel(@ModelAttribute CarModel carModel, WebRequest wr){
        carModel.setBrand(wr.getParameter("brand"));
        carModel.setModelName(wr.getParameter("name"));
        carModel.setFuelTypeID(service.getMatchingID(wr.getParameter("fuelType"),
                                                        service.fetchAllFuelTypes()));
        service.addCarModel(carModel);
        return "redirect:/carDashboard";
    }

    @PostMapping("/removeCar")
    public String removeCar(){
        return "redirect:/carDashboard";
    }

    @PostMapping("/removeCarModel")
    public String removeCarModel(){
        return "redirect:/carDashboard";
    }

    @PostMapping("/editCar")
    public String editCar(){
        return "redirect:/carForm";
    }

    @PostMapping("/editCarModel")
    public String editCarModel(){
        return "redirect:/carmodelForm";
    }

}
