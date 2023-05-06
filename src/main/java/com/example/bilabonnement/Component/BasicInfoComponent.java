package com.example.bilabonnement.Component;
import com.example.bilabonnement.Service.Service;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class BasicInfoComponent {
    private List<String> locationList;
    private HashMap<String, String> locationMap;
    private List<String> employeePositionList;
    private List<String> carHealthStatusList;

    public enum Fuel {
        BENZENE, DIESEL, GASOLINE, ELECTRIC
    }

    @Autowired
    Service service;

    @PostConstruct
    public void populate(){
//        locationList = service.fetchLocationsAsList();
//        locationMap = service.fetchLocationsAsMap();
//        employeePositionList = service.fetchEmployeePositionsAsList();
//        carHealthStatusList = service.fetchCarHealthStatusAsList();
    }

    public List<String> getLocationList() {
        return locationList;
    }

    public HashMap<String, String> getLocationMap() {
        return locationMap;
    }

    public List<String> getEmployeePositionList() {
        return employeePositionList;
    }

    public List<String> getCarHealthStatusList() {
        return carHealthStatusList;
    }
}
