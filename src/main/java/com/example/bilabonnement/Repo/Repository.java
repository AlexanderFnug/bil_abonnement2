package com.example.bilabonnement.Repo;
import com.example.bilabonnement.EmployeeMapper;
import com.example.bilabonnement.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@org.springframework.stereotype.Repository
public class Repository {

    @Autowired
    JdbcTemplate db;

    //Add methods
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO Employee (user_id, password, position_id, salary) VALUES (?, ?, ?, ?)";
        db.update(sql, employee.getUserID(), employee.getPassword(), employee.getPositionID(), employee.getSalary());
    }

    public void addUser(User user) {
        String sql = "INSERT INTO User (first_name, last_name, email, phone_number, address) VALUES (?, ?, ?, ?, ?)";
        db.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getAddress());
    }

    public void addCar(Car car) {
        String sql = "INSERT INTO Car (model_id, mileage, year, status_id) VALUES (?, ?, ?, ?)";
        db.update(sql, car.getModelID(), car.getMileage(), car.getYear(), car.getStatusID());
    }

    public void addCarModel(CarModel carModel) {
        String sql = "INSERT INTO CarModel (brand, name, fuel_type_id) VALUES (?, ?, ?)";
        db.update(sql, carModel.getBrand(), carModel.getName(), carModel.getFuelType());
    }

    public void addFuelType(String fuelType) {
        String sql = "INSERT INTO FuelType (fuel_type) VALUES (?)";
        db.update(sql, fuelType);
    }

    public void addEmployeePosition(String employeePosition) {
        String sql = "INSERT INTO EmployeePosition (title) VALUES (?)";
        db.update(sql, employeePosition);
    }

    public void addLocation(String location) {
        String sql = "INSERT INTO Location (address) VALUES (?)";
        db.update(sql, location);
    }

    public void addLease(Lease lease) {
        String sql = "INSERT INTO leases (car_id, user_id, employee_id, date_start, date_end, date_return, location_pickup, location_return, max_mileage, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        db.update(sql, lease.getCarID(), lease.getUserID(), lease.getEmployeeID(), lease.getDateStart(), lease.getDateEnd(), lease.getDateReturn(), lease.getLocationPickup(), lease.getLocationReturn(), lease.getMaxMileage(), lease.getPrice());
    }

    public void addDamageReport(DamageReport report) {
        String sql = "INSERT INTO DamageReport (description, lease_id, employee_id,  cost, date_accident, date_report) VALUES (?, ?, ?, ? ,? ,?)";
        db.update(sql, report.getDescription(), report.getLeaseID(), report.getEmployeeID(), report.getCost(),
                report.getDateAccident(), report.getDateReport());
    }
    //Fetch all

    public List<Employee> fetchAllEmployees() {
        String sql = "SELECT employee_id, employee.user_id, password, position_id, salary, first_name, last_name, " +
                "email, phone_number, address FROM Employee JOIN User ON Employee.user_id = User.user_id";
        RowMapper<Employee> rowMapper = new EmployeeMapper();
        return db.query(sql, rowMapper);
    }

    public List<User> fetchAllUsers() {
        String sql = "SELECT * FROM User";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return db.query(sql, rowMapper);
    }

    public List<Lease> fetchAllLeases() {
        String sql = "SELECT * FROM Lease";
        RowMapper<Lease> rowMapper = new BeanPropertyRowMapper<>(Lease.class);
        return db.query(sql, rowMapper);
    }

    public List<DamageReport> fetchAllDamageReports() {
        String sql = "SELECT * FROM DamageReport";
        RowMapper<DamageReport> rowMapper = new BeanPropertyRowMapper<>(DamageReport.class);
        return db.query(sql, rowMapper);
    }

    public List<Car> fetchAllCars() {
        String sql = "SELECT * FROM car";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return db.query(sql, rowMapper);
    }

    public List<CarModel> fetchAllCarModels() {
        String sql = "SELECT * FROM CarModel";
        RowMapper<CarModel> rowMapper = new BeanPropertyRowMapper<>(CarModel.class);
        return db.query(sql, rowMapper);
    }

    public List<String> fetchAllLocations() {
        String sql = "SELECT * FROM Location";
        RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
        return db.query(sql, rowMapper);
    }

    public List<String> fetchAllFuelTypes() {
        String sql = "SELECT * FROM FuelType";
        RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
        return db.query(sql, rowMapper);
    }

    public List<String> fetchAllEmployeePositions() {
        String sql = "SELECT * FROM EmployeePosition";
        RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
        return db.query(sql, rowMapper);
    }

    //Getters by ID

    public Employee getEmployeeByID(int employeeID) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee emp = db.queryForObject(sql, rowMapper, employeeID);
        return emp;
    }

    public Car getCarByID(int carID) {
        String sql = "SELECT * FROM cars WHERE car_id = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        Car c = db.queryForObject(sql, rowMapper, carID);
        return c;
    }

    public DamageReport getDamageReportByID(int damageReportID) {
        String sql = "SELECT * FROM CarDamageReport WHERE report_id = ?";
        RowMapper<DamageReport> rowMapper = new BeanPropertyRowMapper<>(DamageReport.class);
        DamageReport d = db.queryForObject(sql, rowMapper, damageReportID);
        return d;
    }

    public Lease getLeaseByID(int leaseID) {
        String sql = "SELECT * FROM leases WHERE lease_id = ?";
        RowMapper<Lease> rowMapper = new BeanPropertyRowMapper<>(Lease.class);
        Lease l = db.queryForObject(sql, rowMapper, leaseID);
        return l;
    }

    public User getUserByID(int userID) {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User u = db.queryForObject(sql, rowMapper, userID);
        return u;
    }

    //Delete Methods
    //TODO: Needs to be updated. Will cause SQL exceptions !!

    public void deleteDamageReportByID(int damageReportID) { //removes one row from item database
        String sql = "DELETE FROM damagereport WHERE damageReportID = ?";
        db.update(sql, damageReportID);
    }

    public void deleteEmployeeByID(int employeeID) { //removes one row from item database
        String sql = "DELETE FROM employees WHERE employeeID = ?";
        db.update(sql, employeeID);
    }

    public void deleteLeaseByID(int leaseID) { //removes one row from item database
        String sql = "DELETE FROM leases WHERE leaseID = ?";
        db.update(sql, leaseID);
    }

    public void deleteCustomerByID(int customerID) { //removes one row from item database
        String sql = "DELETE FROM customers WHERE customerID = ?";
        db.update(sql, customerID);
    }

    public void deleteCarByID(int carID) {
        String sql = "DELETE FROM cars WHERE carID = ?";
        db.update(sql, carID);
    }

    public void deleteUserByUsername(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        db.update(sql, username);
    }

    //Update methods

    public void updateLease(Lease lease) {
        String sql = "UPDATE Lease SET car_id = ?, user_id = ?, employee_id = ?, date_start = ?, date_end = ?, date_return = ?, location_pickup = ?, location_return = ?, max_mileage = ?, price = ? WHERE leaseID = ?";
        db.update(sql, lease.getCarID(), lease.getUserID(), lease.getEmployeeID(),
                lease.getDateStart(), lease.getDateEnd(), lease.getDateReturn(), lease.getLocationPickup(),
                lease.getLocationReturn(), lease.getMaxMileage(), lease.getPrice(), lease.getLeaseID());
    }

    public void updateEmployee(Employee employee) {
        String sql = "UPDATE Employee SET user_id = ?, password = ?, position_id = ?, salary = ? WHERE employee_id = ?";
        db.update(sql, employee.getUserID(), employee.getPassword(), employee.getPositionID(), employee.getSalary(), employee.getEmployeeID());
    }

    public void updateUser(User user) {
        String sql = "UPDATE User SET first_name = ?, last_name = ?, email = ?, phone_number = ?, address = ? WHERE UserID = ?";
        db.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getUserID());
    }

    public void updateDamageReport(DamageReport report) {
        String sql = "UPDATE CarDamageReport SET description = ?, date_accident = ?, date_report = ?, lease_id = ?, employee_id = ?, cost = ? WHERE reportID = ?";
        db.update(sql, report.getDescription(), report.getDateAccident(), report.getDateReport(), report.getLeaseID(), report.getEmployeeID(), report.getCost(), report.getReportID());
    }

    public void updateCar(Car car) {
        String sql = "UPDATE Car SET model_id = ?, mileage = ?, year = ?, status_id = ? WHERE carID = ?";
        db.update(sql, car.getModelID(), car.getMileage(), car.getYear(), car.getStatusID(), car.getCarID());
    }

}
