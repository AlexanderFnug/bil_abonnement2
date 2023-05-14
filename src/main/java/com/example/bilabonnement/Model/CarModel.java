package com.example.bilabonnement.Model;

public class CarModel {
    private int modelID;
    private String brand;
    private int fuelTypeID;
    private String modelName;

    public CarModel() {
        //Empty constructor
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getFuelTypeID() {
        return fuelTypeID;
    }

    public void setFuelTypeID(int fuelTypeID) {
        this.fuelTypeID = fuelTypeID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
