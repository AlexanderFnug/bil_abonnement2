package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.Employee;
import com.example.bilabonnement.Model.Lease;
import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.HashMap;
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
public class LeaseController {
    @Autowired
    Service service;

    @GetMapping("/leaseDashboard")
    public String leaseDashboard(Model model){
        List<HashMap> mergedMapList = service.getMergedLeaseListAsMap(); //Merges leases, cars and users into one ordered list of maps.
        model.addAttribute("mergedMapList", mergedMapList);
        return "leasedashboard.html";
    }

    @GetMapping("/leaseForm")
    public String leaseForm(Model model, WebRequest wr){
        List<Lease> leaseList = service.fetchAllLeases();
        List<Car> leaseableCarList = service.fetchAllCars();
        List<User> userList = service.fetchAllUsers();
        List<Employee> employeeList = service.fetchAllEmployees();
        List<String> locationList = service.fetchAllLocations();

        //Remove already leased cars TODO: Move to service layer
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

        //Ugly code to calculate pre-set end date - TODO: Move to service layer
        LocalDate endDate = LocalDate.now();
        switch (wr.getParameter("leaseLength")){
            case "lease3M":
                endDate = endDate.plusMonths(3);
                break;
            case "lease6M":
                endDate = endDate.plusMonths(6);
                break;
            case "leaseYear":
                endDate = endDate.plusYears(1);
                break;
        }
        model.addAttribute("leaseType",endDate);
        return "leaseform.html";
    }

    @PostMapping("/addLease")
    public String addLease(@ModelAttribute Lease lease, WebRequest wr){
        lease.setUserID(Integer.parseInt(wr.getParameter("user")));
        lease.setEmployeeID(Integer.parseInt(wr.getParameter("employee")));
        lease.setDateStart(wr.getParameter("startDate"));
        lease.setDateEnd(wr.getParameter("endDate"));
        lease.setCarID(Integer.parseInt(wr.getParameter("car")));
        lease.setMaxMileage(Integer.parseInt(wr.getParameter("maxMileage")));
        lease.setPrice(Double.parseDouble(wr.getParameter("price")));

        //Since we don't have a location model(unfortunately), location objects are just String objects.
        //We need to match the string with its database equivalent to get the ID. TODO: Create location model as extension of the String class but with ID
        lease.setLocationPickupID(service.getMatchingID(wr.getParameter("locationPickup"),
                                    service.fetchAllLocations()));
        lease.setLocationReturnID(service.getMatchingID(wr.getParameter("locationReturn"),
                                    service.fetchAllLocations()));

        service.addLease(lease);
        return "leasedashboard.html";
    }

    @PostMapping("/removeLease")
    public String removeLease(WebRequest wr){
        int tempID = Integer.parseInt(wr.getParameter("leaseID"));
        List<Lease> activeLeaseList = service.getActiveLeases();

        //Check if lease is active
        breakableLoop:
        for (Lease lease : activeLeaseList) {
            if (lease.getLeaseID() == tempID){
                //TODO: Show error popup
                break breakableLoop;
            }
        }
        return "redirect:/leasedashboard";
    }

    @PostMapping("/editLease")
    public String editLease(WebRequest wr, Model model){
        Lease tempLease = service.getLeaseByID(Integer.parseInt(wr.getParameter("leaseID")));
        model.addAttribute("lease", tempLease);

        //COPYPASTED CODE FROM leaseForm(): TODO: Move stuff to service. Other solution to avoid redundancy?
        List<Lease> leaseList = service.fetchAllLeases();
        List<Car> leaseableCarList = service.fetchAllCars();
        List<User> userList = service.fetchAllUsers();
        List<Employee> employeeList = service.fetchAllEmployees();
        List<String> locationList = service.fetchAllLocations();

        //Remove already leased cars TODO: Move to service layer
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

        return "redirect:/leaseform";
    }

}
