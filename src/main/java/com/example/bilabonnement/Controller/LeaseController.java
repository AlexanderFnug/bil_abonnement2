package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Component.BasicInfoComponent;
import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.Employee;
import com.example.bilabonnement.Model.Lease;
import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class LeaseController {
    @Autowired
    Service service;

    @Autowired
    BasicInfoComponent basicInfo;

    @GetMapping("/leaseDashboard")
    public String leaseDashboard(){
        return "leasedashboard.html";
    }

    @PostMapping("/leaseForm")
    public String leaseForm(Model model, HttpSession session){
        List<Lease> leaseList = service.fetchAllLeases();
        List<Car> leaseableCarList = service.fetchAllCars();
        List<User> userList = service.fetchAllUsers();
        List<Employee> employeeList = service.fetchAllEmployees();
        List<String> locationList = basicInfo.getLocationList();
        //Remove already leased cars
        for (int i = 0; i < leaseableCarList.size(); i++) {
            for (Lease l : leaseList) {
                if (leaseableCarList.get(i).getCarID() == l.getCarID()){
                    leaseableCarList.remove(i);
                }
            }
        }
        model.addAttribute("carList",leaseableCarList);
        model.addAttribute("userList",userList);
        model.addAttribute("employeeList",employeeList);
        model.addAttribute("locationList",locationList);
        return "leaseform.html";
    }

    @PostMapping("/addLease")
    public String addLease(@ModelAttribute Lease lease, WebRequest wr){
//        List<Lease> leaseList = service.fetchAllLeases();
//        List<Car> carList = service.fetchAllCars();
//        List<User> userList = service.fetchAllUsers();
//        List<Employee> employeeList = service.fetchAllEmployees();
//        List<String> locationList = service.fetchAllLocations();
        lease.setUserID(Integer.parseInt(wr.getParameter("user")));
        System.out.println("333");
        lease.setDateStart(wr.getParameter("startDate"));
        lease.setDateEnd(wr.getParameter("endDate"));
        lease.setDateReturn(wr.getParameter("returnDate"));

        //
        Map<String, String[]> tempMap = wr.getParameterMap();
        tempMap.remove("current");
        tempMap.remove("count");
        tempMap.remove("size");
        System.out.println(tempMap.get("locationPickup"));
        System.out.println(tempMap.get("locationReturn"));
        lease.setLocationPickupID(Integer.parseInt(tempMap.get("locationPickup")[0])+1);
        lease.setLocationReturnID(Integer.parseInt(tempMap.get("locationReturn")[0])+1);
        System.out.println("555");
        lease.setMaxMileage(Integer.parseInt(wr.getParameter("maxMileage")));
        lease.setPrice(Double.parseDouble(wr.getParameter("price")));
        System.out.println("222");
        service.addLease(lease);
        System.out.println("222");
        return "leasedashboard.html";
    }

    @PostMapping("/removeLease")
    public String removeLease(){
        return "redirect:/leasedashboard";
    }

    @PostMapping("/editLease")
    public String editLease(){
        return "redirect:/leaseform";
    }

}
