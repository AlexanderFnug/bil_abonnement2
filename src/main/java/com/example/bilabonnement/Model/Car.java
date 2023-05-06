package com.example.bilabonnement.Model;
import com.example.bilabonnement.Component.BasicInfoComponent.Fuel;

public class Car {
    private int carID;
    private int modelID;
    private int modelYear;
    private String brandName;
    private String modelName;
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

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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
