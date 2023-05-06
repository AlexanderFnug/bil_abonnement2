package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {
    @Autowired
    Service service;

    @GetMapping("/carDashboard")
    public String carDashboard(){
        return "cardashboard.html";
    }

    @GetMapping("/carModelDashboard")
    public String carModelDashboard(){
        return "carmodeldashboard.html";
    }

    @GetMapping("/carForm")
    public String carForm(){
        return "carform.html";
    }

    @GetMapping("/carModelForm")
    public String carModelForm(){
        return "carmodelform.html";
    }

    @PostMapping("/addCar")
    public String addCar(){
        return "redirect:/cardashboard";
    }

    @PostMapping("/addCarModel")
    public String addCarModel(){
        return "redirect:/carmodeldashboard";
    }

    @PostMapping("/removeCar")
    public String removeCar(){
        return "redirect:/cardashboard";
    }

    @PostMapping("/removeCarModel")
    public String removeCarModel(){
        return "redirect:/carmodeldashboard";
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
