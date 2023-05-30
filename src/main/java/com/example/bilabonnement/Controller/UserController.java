package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Employee;
import com.example.bilabonnement.Model.User;
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
public class UserController {
    @Autowired
    Service service;

    @GetMapping("/userDashboard")  //User dashboard
    public String userDashboard(Model model){
        List<User> userList = service.fetchAllUsers();
        List<Employee> employeeList = service.fetchAllEmployees();

        //Filter out employees TODO: move to service layer, database redesign?
        //Currently the user table in the database contains EVERYONE, so we need to filter out those also listed as employees
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < employeeList.size(); j++) {
                if (userList.get(i).getUserID() == employeeList.get(j).getUserID()){
                    userList.remove(i);
                }
            }
        }

        model.addAttribute("filteredUserList", userList);
        return "userdashboard.html";
    }
    @GetMapping("/userForm")       //User register page
    public String userForm(){
        return "userform.html";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, WebRequest wr){
        user.setFirstName(wr.getParameter("firstName"));
        user.setLastName(wr.getParameter("lastName"));
        user.setEmail(wr.getParameter("email"));
        user.setPhoneNumber(wr.getParameter("phoneNumber"));
        user.setAddress(wr.getParameter("address"));
        service.addUser(user);
        return ("redirect:/userDashboard");
    }

    @PostMapping("/removeUser")
    public String removeUser(){
        return ("redirect:/userDashboard");
    }

    @PostMapping("/editUser")
    public String editUser(){
        return ("redirect:/userForm");
    }
}
