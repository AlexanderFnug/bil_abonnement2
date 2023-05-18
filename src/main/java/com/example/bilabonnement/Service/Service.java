package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.*;
import com.example.bilabonnement.Repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    Repository repo;

    public Integer getMatchingID(String strToMatch, List list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(strToMatch)) {
                return i+1;
            }
        }
        return null;
    }

    public List<Lease> getActiveLeases() {
        List<Lease> activeLeaseList = fetchAllLeases();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date leaseEndDate = new Date();
        Date currentDate = new Date();
        for (int i = 0; i < activeLeaseList.size(); i++) {
                try {
                    leaseEndDate = df.parse(activeLeaseList.get(i).getDateEnd());
                } catch(ParseException e) {
                    e.printStackTrace();
                }
                if (leaseEndDate.before(currentDate)){
                    activeLeaseList.remove(i);
                }
        }
        return activeLeaseList;
    }
    public double getLeasedTotal(){
        Double total = 0.0;
        List<Lease> leaseList = fetchAllLeases();
        for (Lease lease : leaseList) {
            total += lease.getPrice();
        }
        return total;
    }
    public List<Object[]> getMergedList(){
        List<Object[]> mergedList = new ArrayList<>();
        List<User> tempUserList = fetchAllUsers();
        List<Car> tempCarList = fetchAllCars();
        List<Lease> tempLeaseList = fetchAllLeases();
        for (Car car: tempCarList) {
            Object[] tempObjArr = new Object[3];
            tempObjArr[0] = car;
            for (Lease lease : tempLeaseList) {
                if (tempObjArr[1] == null && car.getCarID() == lease.getCarID()){
                    tempObjArr[1] = lease;
                    for (User user : tempUserList) {
                        if (lease.getUserID() == user.getUserID()){
                            tempObjArr[2] = user;
                        }
                    }
                }
            }
            mergedList.add(tempObjArr);
        }
        return mergedList;
    }

    public Integer userVerification(Employee employee) {
        List<Employee> tempEmployeeList = repo.fetchAllEmployees();
        for (Employee listEmp : tempEmployeeList) {
            if (employee.getEmail().equals(listEmp.getEmail()) && employee.getPassword().equals(listEmp.getPassword())) {
                return listEmp.getEmployeeID();
            }
        }
        return null;
    }

    //add Methods

    public void addEmployee(Employee employee) {
        repo.addEmployee(employee);
    }

    public void addUser(User user) {
//        List<User> tempUserList = repo.fetchAllUsers();
//        for (User listUser : tempUserList) {
//            if (user.getEmail().equals(listUser.getEmail())) {
//                return;
//            }
//        }
        repo.addUser(user);
    }

    public void addCar(Car car) {
        repo.addCar(car);
    }

    public void addCarModel(CarModel carModel) {
        repo.addCarModel(carModel);
    }

    public void addDamageReport(DamageReport damageReport) {
        repo.addDamageReport(damageReport);
    }

    public void addLease(Lease lease) {
        repo.addLease(lease);
    }

    public void addLocation(String location) {
        repo.addLocation(location);
    }

    public void addEmployeePosition(String position) {
        repo.addEmployeePosition(position);
    }

    public void addFuelType(String fuelType) {
        repo.addFuelType(fuelType);
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

    public List<Car> fetchAllCars() {
        return repo.fetchAllCars();
    }

    public List<CarModel> fetchAllCarModels() {
        return repo.fetchAllCarModels();
    }

    public List<String> fetchAllLocations() {
        return repo.fetchAllLocations();
    }

    public List<String> fetchAllFuelTypes() {
        return repo.fetchAllFuelTypes();
    }

    public List<String> fetchEmployeePositions() {
        return repo.fetchAllEmployeePositions();
    }

    //Get by ID methods

    public Employee getEmployeeByID(int employeeID) {
        return repo.getEmployeeByID(employeeID);
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

    public void deleteDamageReportByID(int damageReportID) {
        repo.deleteDamageReportByID(damageReportID);
    }

    public void deleteEmployeeByID(int employeeID) {
        repo.deleteEmployeeByID(employeeID);
    }

    public void deleteLeaseByID(int leaseID) {
        repo.deleteLeaseByID(leaseID);
    }

    public void deleteCustomerByID(int customerID) {
        repo.deleteCustomerByID(customerID);
    }

    public void deleteCarByID(int carID) {
        repo.deleteCarByID(carID);
    }

    public void updateUser(User user) {
        repo.updateUser(user);
    }

    public void updateLease(Lease lease) {
        repo.updateLease(lease);
    }

    public void updateEmployee(Employee employee) {
        repo.updateEmployee(employee);
    }

    public void updateDamageReport(DamageReport damageReport) {
        repo.updateDamageReport(damageReport);
    }

    public void updateCar(Car car) {
        repo.updateCar(car);
    }

}
