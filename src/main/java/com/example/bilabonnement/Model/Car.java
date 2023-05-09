package com.example.bilabonnement.Model;
import com.example.bilabonnement.Component.BasicInfoComponent.Fuel;

public class Car {
    private int carID;
    private int Year;

    private String brand;
    private String model;
    private int mileage;

    private int healthStatusID;
    private Fuel fuelType;

    public Car(){
        //Empty constructor
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }



    public int getYear() {
        return Year;
    }

    public void setYear(int modelYear) {
        this.Year = modelYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brandName) {
        this.brand = brandName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getHealthStatusID() {
        return healthStatusID;
    }

    public void setHealthStatusID(int healthStatusID) {
        this.healthStatusID = healthStatusID;
    }

    public Fuel getFuelType() {
        return fuelType;
    }

    public void setFuelType(Fuel fuelType) {
        this.fuelType = fuelType;
    }
}
