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
    private List<String> fuelTypeList;

    public enum CarHealthStatus {
        FINE, PARTIALLY_DAMAGED, HEAVILY_DAMAGED, COMPLETELY_DAMAGED
    }

    @Autowired
    Service service;

    @PostConstruct
    public void populateLists(){
//        locationList = service.fetchLocationsAsList();
//        locationMap = service.fetchLocationsAsMap();
//        employeePositionList = service.fetchEmployeePositionsAsList();
//        fuelTypeList = service.fetchFuelTypesAsList();
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

    public List<String> getFuelTypeList() {
        return fuelTypeList;
    }
}
