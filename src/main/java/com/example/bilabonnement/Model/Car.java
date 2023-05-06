package com.example.bilabonnement.Model;

enum typesOfFuel{
    DIESEL, BENZINE
}

enum health{
    FINE, PARTIALLY_DAMAGED, HEAVILY_DAMAGED, COMPLETELY_DAMAGED
}
public class Car {
    private int carID;
    private int modelID;
    private int modelYear;
    private String brandName;
    private String modelName;
    private typesOfFuel fuelType;
    private int mileage;
    private int healthID;
    private health healthStatus;

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

    public typesOfFuel getFuelType() {
        return fuelType;
    }

    public void setFuelType(typesOfFuel fuelType) {
        this.fuelType = fuelType;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getHealthID() {
        return healthID;
    }

    public void setHealthID(int healthID) {
        this.healthID = healthID;
    }

    public health getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(health healthStatus) {
        this.healthStatus = healthStatus;
    }
}
