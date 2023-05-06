package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {
    @Autowired
    Service service;
}
