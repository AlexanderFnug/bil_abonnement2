package com.example.bilabonnement.Model;

public class Employee extends User {
    private int employeeID;
    private String password; //Maybe shouldn't be saved in the model??
    private int positionID;
    private double salary;

    public Employee(){
        //Empty constructor
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
