package com.example.bilabonnement.Service;


import com.example.bilabonnement.Model.*;
import com.example.bilabonnement.Repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    Repo repo;

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
    public void deleteDamageReportByID(int damageReportID) throws ServiceException {
        try {
            repo.deleteDamageReportByID(damageReportID);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to delete damage report with ID " + damageReportID, e);
        }
    }

    public void deleteEmployeeByID(int employeeID) throws ServiceException {
        try {
            repo.deleteEmployeeByID(employeeID);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to delete employee with ID " + employeeID, e);
        }
    }

    public void deleteLeaseByID(int leaseID) throws ServiceException {
        try {
            repo.deleteLeaseByID(leaseID);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to delete lease with ID " + leaseID, e);
        }
    }

    public void deleteCustomerByID(int customerID) throws ServiceException {
        try {
            repo.deleteCustomerByID(customerID);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to delete customer with ID " + customerID, e);
        }
    }

    public void deleteCarByID(int carID) throws ServiceException {
        try {
            repo.deleteCarByID(carID);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to delete car with ID " + carID, e);
        }
    }

    public void updateUser(User user) throws ServiceException {
        try {
            repo.updateUser(user);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to update user with ID " + user.getUserID(), e);
        }
    }

    public void updateLease(Lease lease) throws ServiceException {
        try {
            repo.updateLease(lease);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to update lease with ID " + lease.getLeaseID(), e);
        }
    }

    public void updateCustomer(Customer customer) throws ServiceException {
        try {
            repo.updateCustomer(customer);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to update customer with ID " + customer.getCustomerID(), e);
        }
    }

    public void updateEmployee(Employee employee) throws ServiceException {
        try {
            repo.updateEmployee(employee);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to update employee with ID " + employee.getEmployeeID(), e);
        }
    }

    public void updateDamageReport(DamageReport damageReport) throws ServiceException {
        try {
            repo.updateDamageReport(damageReport);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to update damage report with ID " + damageReport.getReportID(), e);
        }
    }

    public void updateCar(Car car) throws ServiceException {
        try {
            repo.updateCar(car);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to update car with ID " + car.getCarID(), e);
        }
    }



}
