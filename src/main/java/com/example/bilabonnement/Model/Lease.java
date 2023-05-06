package com.example.bilabonnement.Model;

enum loc{
    KBHJAGTVEJ, VALBY, ÅRHUS
}
public class Lease {
    private int leaseID;
    private Car car;
    private Customer customer;
    private Employee employee;
    private String startDate; //Different datatype?
    private String endDate;
    private String returnDate;
    private loc pickupLocation;
    private loc returnLocation;
    private int maxMileage;
    private double price;

    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getMaxMileage() {
        return maxMileage;
    }

    public void setMaxMileage(int maxMileage) {
        this.maxMileage = maxMileage;
    }

    public loc getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(loc pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public loc getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(loc returnLocation) {
        this.returnLocation = returnLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
