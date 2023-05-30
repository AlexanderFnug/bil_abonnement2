package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.*;
import com.example.bilabonnement.Repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
        List<Lease> leaseList = getActiveLeases();
        for (Lease lease : leaseList) {
            total += lease.getPrice();
        }
        return total;
    }

    /**
     * Gets three lists from the database(leases, cars and users).
     * Then it matches them in a map where appropriate. e.g. Lease object with the userID of 1 is matched with
     * the User object with the userID of 1 etc.
     * The goal was to make managing multiple lists in thymeleaf easier.
     * @return
     */
    public List<HashMap> getMergedLeaseListAsMap(){
        List<HashMap> mergedList = new ArrayList<>();
        List<User> tempUserList = fetchAllUsers();
        List<Car> tempCarList = fetchAllCars();
        List<Lease> tempLeaseList = fetchAllLeases();
        List<Lease> tempActiveLeaseList = getActiveLeases();
        for (Lease lease : tempLeaseList) {
            HashMap<String, Object> mergedMap = new HashMap<>();
            mergedMap.put("lease", lease);
            for (Lease activeLease : tempActiveLeaseList) {
                if (lease.getLeaseID() == activeLease.getLeaseID()){
                    mergedMap.put("status", true);
                }
            }
            for (User user : tempUserList) {
                if (lease.getUserID() == user.getUserID()){
                    mergedMap.put("user", user);
                }
            }
            for (Car car : tempCarList) {
                if (lease.getCarID() == car.getCarID()){
                    mergedMap.put("car", car);
                }
            }
            mergedList.add(mergedMap);
        }
        return mergedList;
    }

    /**
     * Deprecated. Does the same as above, but with an Object array instead of a HashMap.
     * More annoying to use and more prone to errors.
     * @return
     */
    public List<Object[]> getMergedLeaseList(){ //TODO: Use sql query instead??????
        List<Object[]> mergedList = new ArrayList<>();
        List<User> tempUserList = fetchAllUsers();
        List<Car> tempCarList = fetchAllCars();
        List<Lease> tempLeaseList = fetchAllLeases();
        List<Lease> tempActiveLeaseList = getActiveLeases();
        for (Lease lease : tempLeaseList) {
            Object[] tempObjArr = new Object[4]; //0 = lease, 1 = boolean(status), 2 = user, 3 = car
            tempObjArr[0] = lease;
            for (Lease activeLease : tempActiveLeaseList) {
                if (lease.getLeaseID() == activeLease.getLeaseID()){
                    tempObjArr[1] = true;
                }
            }
            for (User user : tempUserList) {
                if (lease.getUserID() == user.getUserID()){
                    tempObjArr[2] = user;
                }
            }
            for (Car car : tempCarList) {
                if (lease.getCarID() == car.getCarID()){
                    tempObjArr[3] = car;
                }
            }
            mergedList.add(tempObjArr);
        }
        return mergedList;
    }

    /**
     * Same as getMergedLeaseListAsMap. But with an Object array instead.
     * @return
     */
    public List<Object[]> getMergedCarList(){ //TODO: Change use of object array to HashMap.
        List<Object[]> mergedList = new ArrayList<>();
        List<User> tempUserList = fetchAllUsers();
        List<Car> tempCarList = fetchAllCars();
        List<Lease> tempLeaseList = fetchAllLeases();
        List<Lease> tempActiveLeaseList = getActiveLeases();
        for (Car car: tempCarList) {
            Object[] tempObjArr = new Object[3];
            tempObjArr[0] = car;
            for (Lease lease : tempLeaseList) {
                for (Lease activeLease : tempActiveLeaseList) {
                    if (tempObjArr[1] == null &&
                                car.getCarID() == lease.getCarID() &&
                                lease.getLeaseID() == activeLease.getLeaseID()){

                        tempObjArr[1] = lease;
                        for (User user : tempUserList) {
                            if (lease.getUserID() == user.getUserID()){
                                tempObjArr[2] = user;
                            }
                        }
                    }
                }
            }
            mergedList.add(tempObjArr);
        }
        return mergedList;
    }

    /**
     * Service is given a temporary employee object containing password and email.
     * It looks for a match in the database and returns the full User object.
     * @param employee
     * @return
     */
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
