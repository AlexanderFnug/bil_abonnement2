package com.example.bilabonnement.Model;

import java.util.Date;

public class DamageReport {
    private int reportID;
    private String description;
    private int leaseID;
    private int employeeID;
    private double cost;
    private String dateAccident;
    private String dateReport;

    public DamageReport() {
        //Empty constructor
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDateAccident() {
        return dateAccident;
    }

    public void setDateAccident(String dateAccident) {
        this.dateAccident = dateAccident;
    }

    public String getDateReport() {
        return dateReport;
    }

    public void setDateReport(String dateReport) {
        this.dateReport = dateReport;
    }
}
