package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Employee;
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
public class EmployeeController {
    @Autowired
    Service service;

    @GetMapping("/employeeDashboard")
    public String employeeDashboard(Model model){
        List<String> positionList = service.fetchEmployeePositions();
        List<Employee> employeeList = service.fetchAllEmployees();
        model.addAttribute("positionList", positionList);
        model.addAttribute("employeeList", employeeList);
        return "employeedashboard.html";
    }

    @GetMapping("/employeeForm")
    public String employeeForm(Model model){
        List<String> positionList = service.fetchEmployeePositions();
        List<Employee> userList = service.fetchAllEmployees();
        model.addAttribute("positionList", positionList);
        model.addAttribute("userList", userList);
        return "employeeform.html";
    }
    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee, WebRequest wr){
        employee.setUserID(Integer.parseInt(wr.getParameter("user")));
        employee.setSalary(Integer.parseInt(wr.getParameter("salary")));
        employee.setPassword(wr.getParameter("password"));
        employee.setPositionID(service.getMatchingID(wr.getParameter("position"),
                service.fetchEmployeePositions()));
        service.addEmployee(employee);
        return "redirect:/employeeDashboard";
    }

    @PostMapping("/removeEmployee")
    public String removeEmployee(){
        return "redirect:/mainDashboard";
    }

    @PostMapping("/editEmployee")
    public String editEmployee(){
        return "redirect:/employeeForm";
    }

}
