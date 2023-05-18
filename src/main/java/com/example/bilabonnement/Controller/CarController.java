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

import java.time.LocalDate;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    Service service;

    @GetMapping("/carDashboard")
    public String carDashboard(){
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
        car.setModelID(Integer.parseInt(wr.getParameter("modelID")));
        car.setYear(Integer.parseInt(wr.getParameter("year")));
        car.setMileage(Integer.parseInt(wr.getParameter("mileage")));
        service.addCar(car);
        return "redirect:/cardashboard";
    }

    @PostMapping("/addCarModel")
    public String addCarModel(@ModelAttribute CarModel carModel, WebRequest wr){
        carModel.setBrand(wr.getParameter("brand"));
        carModel.setModelName(wr.getParameter("name"));
        carModel.setFuelTypeID(service.getMatchingID(wr.getParameter("fuelType"),
                                                        service.fetchAllFuelTypes()));
        service.addCarModel(carModel);
        return "redirect:/cardashboard";
    }

    @PostMapping("/removeCar")
    public String removeCar(){
        return "redirect:/cardashboard";
    }

    @PostMapping("/removeCarModel")
    public String removeCarModel(){
        return "redirect:/cardashboard";
    }

    @PostMapping("/editCar")
    public String editCar(){
        return "redirect:/carform";
    }

    @PostMapping("/editCarModel")
    public String editCarModel(){
        return "redirect:/carmodelform";
    }

}
