package com.example.bilabonnement.Service;


import com.example.bilabonnement.Model.*;
import com.example.bilabonnement.Repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    Repo repo;


    public Integer validation(User user){
        List<User> tempUsers = repo.fetchAllUsers();
        System.out.println(tempUsers);
        for (User listUser: tempUsers) {
            if(user.getUsername().equals(listUser.getUsername())
                    && user.getPassword().equals(listUser.getPassword())){
                return listUser.getUserID();
            }
        }
        return null;
    }

        //add Methods

    public void addEmployee(Employee employee) {
        repo.addEmployee(employee);
    }

    public void addUser(User user) {
        repo.addUser(user);
    }

    public void addCar(Car car) {
        repo.addCar(car);
    }

    public void addDamageReport(DamageReport damageReport) {
        repo.addDamageReport(damageReport);
    }

    public void addLease(Lease lease) {
        repo.addLease(lease);
    }

    public void addCustomer(Customer customer) {
        repo.addCustomer(customer);
    }

        //Fetch all methods

    public List<Employee> fetchAllEmployees() {
        return repo.fetchAllEmployees();
    }

    public List<User> fetchAllUsers() {
        return repo.fetchAllUsers();
    }

    public List<Lease> fetchAllLeases() {
        return repo.fetchAllLeases();
    }

    public List<DamageReport> fetchAllDamageReports() {
        return repo.fetchAllDamageReports();
    }

    public List<Customer> fetchAllCustomers() {
        return repo.fetchAllCustomers();
    }

    public List<Car> fetchAllCars() {
        return repo.fetchAllCars();
    }

        //Get by ID methods

    public Employee getEmployeeByID(int employeeID) {
        return repo.getEmployeeByID(employeeID);
    }

    public Customer getCustomerByID(int customerID) {
        return repo.getCustomerByID(customerID);
    }

    public Car getCarByID(int carID) {
        return repo.getCarByID(carID);
    }

    public DamageReport getDamageReportByID(int damageReportID) {
        return repo.getDamageReportByID(damageReportID);
    }

    public Lease getLeaseByID(int leaseID) {
        return repo.getLeaseByID(leaseID);
    }

    public User getUserByID(int userID) {
        return repo.getUserByID(userID);
    }
    public void deleteDamageReportByID(int damageReportID)  {

            repo.deleteDamageReportByID(damageReportID);

    }

    public void deleteEmployeeByID(int employeeID) {

            repo.deleteEmployeeByID(employeeID);

    }

    public void deleteLeaseByID(int leaseID)  {

            repo.deleteLeaseByID(leaseID);

    }

    public void deleteCustomerByID(int customerID) {

            repo.deleteCustomerByID(customerID);

    }

    public void deleteCarByID(int carID)  {

            repo.deleteCarByID(carID);

    }

    public void updateUser(User user)  {

            repo.updateUser(user);

    }

    public void updateLease(Lease lease) {

            repo.updateLease(lease);
    }

    public void updateCustomer(Customer customer)  {

            repo.updateCustomer(customer);

    }

    public void updateEmployee(Employee employee)  {

            repo.updateEmployee(employee);

    }

    public void updateDamageReport(DamageReport damageReport)  {

            repo.updateDamageReport(damageReport);

    }

    public void updateCar(Car car) {

            repo.updateCar(car);

    }

}
