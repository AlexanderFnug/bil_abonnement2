package com.example.bilabonnement.Component;
import com.example.bilabonnement.Model.Location;
import com.example.bilabonnement.Service.Service;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class BasicInfoComponent {
    private List<Location> locationList;
    private HashMap<String, Location> locationMap;
    private List<String> employeePositionList;
    private List<String> carHealthStatusList;
    private List<Fuel> fuelTypeList;

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
        for (Fuel f : Fuel.values()) {
            fuelTypeList.add(f);
        }
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public HashMap<String, Location> getLocationMap() {
        return locationMap;
    }

    public List<String> getEmployeePositionList() {
        return employeePositionList;
    }

    public List<String> getCarHealthStatusList() {
        return carHealthStatusList;
    }

    public List<Fuel> getFuelTypeList() {
        return fuelTypeList;
    }
}
