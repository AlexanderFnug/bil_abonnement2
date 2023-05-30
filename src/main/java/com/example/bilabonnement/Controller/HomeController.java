package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Employee;
import com.example.bilabonnement.Service.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {
    @Autowired
    Service service;

    /**
     * @author Kian Lund
     * @return index.html
     * This is the 'default' page when the URL doesn't contain anything other than the IP address of the server.
     * Index functions as our login page.
     */
    @GetMapping("/")                //Login page
    public String index(){
        return "index.html";
    }

    /**
     * Makes use of HttpSession to manage user sessions. Each session is unique and will be stored till the user
     * logs out or the session times out itself.
     * Model is used to pass general information from the database, so thymeleaf can access it.
     * WebRequest allows us to return html elements on a page in the form of a map.
     * The method checks if a currentUser is logged ind, if not: they are returned to the login page,
     * it then sets up the necessary information for the dashboard to function with various lists.
     * Finally changeTab is checked. There are three different tabs/views in maindashboard.html,
     * One for all cars, one for leased cars and one for available cars. These buttons all use the
     * "/mainDashboard" mapping, and set changeTab to a specific value when pressed. In the
     * html file, thymeleaf handles the value of changeTab and displays the requested information.
     * @author Kian Lund
     * @param session
     * @param model
     * @param wr
     * @return maindashboard.html
     */
    @GetMapping("/mainDashboard")
    public String mainDashboard(HttpSession session, Model model, WebRequest wr){
        if (session.getAttribute("currentUser") == null){
            return "redirect:/index";
        }
        model.addAttribute("mergedList", service.getMergedCarList()); //Matches user/lease/car objects in one single array and adds the array to a list, filters inactive leases
        model.addAttribute("totalLeaseValue", service.getLeasedTotal());
        model.addAttribute("activeLeases", service.getActiveLeases());
        model.addAttribute("totalLeasedCars", service.getActiveLeases().size());

        if (wr.getParameter("changeTab") != null) {
            model.addAttribute("currentTab", wr.getParameter("changeTab"));
        } else {
            model.addAttribute("currentTab", "all");
        }

        return "maindashboard.html";
    }

    /**
     * Sets up a temporary Employee object which holds login information, the object is then passed to the service
     * layer to check if the email and password matches what is in the database. WebRequest to get the values from
     * the text fields. HttpSession is used to establish a session.
     * @param loginInfo
     * @param wr
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@ModelAttribute Employee loginInfo, WebRequest wr, HttpSession session){
        loginInfo.setEmail(wr.getParameter("email"));
        loginInfo.setPassword(wr.getParameter("password"));
        Integer tempUserID = service.userVerification(loginInfo);
        if (tempUserID != null) {
            Employee tempEmp = service.getEmployeeByID(tempUserID);
            session.setAttribute("currentUser", tempEmp);
            return "redirect:/mainDashboard";
        }
        return "redirect:/";
    }

    /**
     * Simple logout method that returns the user to index.html and terminates the current HttpSession.
     * @param session
     * @return
     */
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
