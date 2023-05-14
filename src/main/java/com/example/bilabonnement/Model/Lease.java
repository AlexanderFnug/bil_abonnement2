package com.example.bilabonnement.Model;

import java.util.Date;

public class Lease {
    private int leaseID;
    private int carID;
    private int userID;
    private int employeeID;
    private String dateStart;
    private String dateEnd;
    private String dateReturn;
    private int locationPickupID;
    private int locationReturnID;
    private int maxMileage;
    private double price;

    public Lease() {
        //Empty constructor
    }

    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public int getLocationPickupID() {
        return locationPickupID;
    }

    public void setLocationPickupID(int locationPickupID) {
        this.locationPickupID = locationPickupID;
    }

    public int getLocationReturnID() {
        return locationReturnID;
    }

    public void setLocationReturnID(int locationReturnID) {
        this.locationReturnID = locationReturnID;
    }

    public int getMaxMileage() {
        return maxMileage;
    }

    public void setMaxMileage(int maxMileage) {
        this.maxMileage = maxMileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
