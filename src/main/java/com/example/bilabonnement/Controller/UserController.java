package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Employee;
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

import java.util.List;

@Controller
public class UserController {
    @Autowired
    Service service;

    @GetMapping("/userDashboard")  //User dashboard
    public String userDashboard(Model model){
        List<User> userList = service.fetchAllUsers();
        List<Employee> employeeList = service.fetchAllEmployees();

        //Filter out employees TODO: move to service layer
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
