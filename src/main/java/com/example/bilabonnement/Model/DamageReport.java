package com.example.bilabonnement.Model;

import java.util.Date;

public class DamageReport {

    private int reportID;
    private String damageDescription = "";
    private int carHealthStatus;
    private Lease lease;
    private Employee employee;
    private double damageCosts;
    private String accidentDate;

    private int carID;

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(String accidentDate) {
        this.accidentDate = accidentDate;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    private String reportDate;

    public String getDamageDescription() {
        return damageDescription;
    }


    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public int getCarHealthStatus() {
        return carHealthStatus;
    }

    public void setCarHealthStatus(int carHealthStatus) {
        this.carHealthStatus = carHealthStatus;
    }

    public double getDamageCosts() {
        return damageCosts;
    }

    public void setDamageCosts(double damageCosts) {
        this.damageCosts = damageCosts;
    }
}
