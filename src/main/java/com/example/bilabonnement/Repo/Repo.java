package com.example.bilabonnement.Repo;

import com.example.bilabonnement.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Repo {

    @Autowired
    JdbcTemplate db;

    public Repo(){
        //Empty constructor
    }

        //Add methods
    public void addEmployee(Employee employee){
        String sql = "INSERT INTO accounts (username, password) VALUES (?, ?)";
        db.update(sql, employee.getFirstName(), employee.getLastName(), employee.getSalary(),
                employee.getPosition(), employee.getEmployeeID());
    }

    public void addUser(User user){
        String sql = "INSERT INTO accounts (username, password) VALUES (?, ?)";
        db.update(sql, user.getUsername(), user.getPassword());
    }
    public void addCar(Car car){
        String sql = "INSERT INTO cars (make, model, year, color, mileage) VALUES (?, ?, ?, ?, ?)";
        db.update(sql, car.getBrand(), car.getModel(), car.getYear(), car.getCarID(), car.getMileage(),
                car.getFuelType(), car.getHealthStatusID());
    }
    public void addDamageReport(DamageReport damageReport){
        String sql = "INSERT INTO damage_reports (carID, description, cost) VALUES (?, ?, ?)";
        db.update(sql, damageReport.getCarID(), damageReport.getDamageDescription(), damageReport.getDamageCosts(),
                damageReport.getReportDate(), damageReport.getAccidentDate(), damageReport.getUserID(),
                damageReport.getReportID(), damageReport.getCarHealthStatus(), damageReport.getCustomerID());
    }
    public void addLease(Lease lease){
        String sql = "INSERT INTO leases (carID, customerID, startDate, endDate, monthlyRate) VALUES (?, ?, ?, ?, ?)";
        db.update(sql, lease.getCarID(), lease.getCustomerID(), lease.getEmployeeID(), lease.getEndDate(),
                lease.getMaxMileage(), lease.getLocationID(), lease.getPrice(), lease.getReturnDate(),
                lease.getStartDate());
    }
    public void addCustomer(Customer customer){
        String sql = "INSERT INTO customers (firstName, lastName, phone, email) VALUES (?, ?, ?, ?)";
        db.update(sql, customer.getFirstName(), customer.getLastName(), customer.getCustomerID(), customer.getEmail(),
                customer.getPhoneNumber(), customer.getAddress());
    }

        //Fetch all

    public List<Employee> fetchAllEmployees(){
        String sql = "SELECT * FROM accounts";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return db.query(sql, rowMapper);
    }

    public List<User> fetchAllUsers(){
        String sql = "SELECT * FROM accounts";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return db.query(sql, rowMapper);
    }
    public List<Lease> fetchAllLeases(){
        String sql = "SELECT * FROM leases";
        RowMapper<Lease> rowMapper = new BeanPropertyRowMapper<>(Lease.class);
        return db.query(sql, rowMapper);
    }
    public List<DamageReport> fetchAllDamageReports(){
        String sql = "SELECT * FROM damage_reports";
        RowMapper<DamageReport> rowMapper = new BeanPropertyRowMapper<>(DamageReport.class);
        return db.query(sql, rowMapper);
    }
    public List<Customer> fetchAllCustomers(){
        String sql = "SELECT * FROM customers";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return db.query(sql, rowMapper);
    }
    public List<Car> fetchAllCars(){
        String sql = "SELECT * FROM cars";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return db.query(sql, rowMapper);
    }


        //Getters by ID

    public Employee getEmployeeByID(int employeeID){
        String sql = "SELECT * FROM accounts WHERE EmployeeID = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee e = db.queryForObject(sql, rowMapper, employeeID);
        return e;
    }
    public Customer getCustomerByID(int customerID){
        String sql = "SELECT * FROM accounts WHERE customerID = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer c = db.queryForObject(sql, rowMapper, customerID);
        return c;
    }
    public Car getCarByID(int carID){
        String sql = "SELECT * FROM cars WHERE carID = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        Car c = db.queryForObject(sql, rowMapper, carID);
        return c;
    }
    public DamageReport getDamageReportByID(int damageReportID){
        String sql = "SELECT * FROM damage_reports WHERE damageReportID = ?";
        RowMapper<DamageReport> rowMapper = new BeanPropertyRowMapper<>(DamageReport.class);
        DamageReport d = db.queryForObject(sql, rowMapper, damageReportID);
        return d;
    }
    public Lease getLeaseByID(int leaseID){
        String sql = "SELECT * FROM leases WHERE leaseID = ?";
        RowMapper<Lease> rowMapper = new BeanPropertyRowMapper<>(Lease.class);
        Lease l = db.queryForObject(sql, rowMapper, leaseID);
        return l;
    }
    public User getUserByID(int userID){
        String sql = "SELECT * FROM accounts WHERE userID = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User u = db.queryForObject(sql, rowMapper, userID);
        return u;
    }

        //Delete Methods

    public void deleteDamageReportByID(int damageReportID){ //removes one row from item database
        String sql = "DELETE FROM damagereport WHERE damageReportID = ?";
        db.update(sql,damageReportID);
    }
    public void deleteEmployeeByID(int employeeID){ //removes one row from item database
        String sql = "DELETE FROM employees WHERE employeeID = ?";
        db.update(sql,employeeID);
    }
    public void deleteLeaseByID(int leaseID){ //removes one row from item database
        String sql = "DELETE FROM leases WHERE leaseID = ?";
        db.update(sql,leaseID);
    }
    public void deleteCustomerByID(int customerID){ //removes one row from item database
        String sql = "DELETE FROM customers WHERE customerID = ?";
        db.update(sql,customerID);
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

    public void updateLease(Lease lease){
        String sql = "UPDATE leases WHERE leaseID = ?";
        db.update(sql, lease.getCarID(), lease.getCustomerID(), lease.getEmployeeID(), lease.getEndDate(),
                lease.getMaxMileage(), lease.getLocationID(), lease.getPrice(), lease.getReturnDate(),
                lease.getStartDate());
    }
    public void updateCustomer(Customer customer){
        String sql = "UPDATE customers SET firstName = ?, lastName = ?, phone = ?, email = ?, address = ? WHERE customerID = ?";
        db.update(sql, customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getEmail(),
                customer.getAddress(), customer.getCustomerID());
    }

    public void updateEmployee(Employee employee){
        String sql = "UPDATE accounts SET username = ?, password = ?, firstName = ?, lastName = ?, salary = ?, position = ? WHERE EmployeeID = ?";
        db.update(sql, employee.getFirstName(), employee.getLastName(),
                employee.getSalary(), employee.getPosition(), employee.getEmployeeID());
    }

    public void updateUser(User user){
        String sql = "UPDATE accounts SET username = ?, password = ? WHERE UserID = ?";
        db.update(sql, user.getUsername(), user.getPassword(), user.getUserID());
    }

    public void updateDamageReport(DamageReport damageReport){
        String sql = "UPDATE damage_reports SET carID = ?, description = ?, cost = ?, reportDate = ?, accidentDate = ?, userID = ?, carHealthStatus = ?, customerID = ? WHERE reportID = ?";
        db.update(sql, damageReport.getCarID(), damageReport.getDamageDescription(), damageReport.getDamageCosts(),
                damageReport.getReportDate(), damageReport.getAccidentDate(), damageReport.getUserID(),
                damageReport.getCarHealthStatus(), damageReport.getCustomerID(), damageReport.getReportID());
    }

    public void updateCar(Car car){
        String sql = "UPDATE cars SET make = ?, model = ?, year = ?, color = ?, mileage = ?, fuelType = ?, healthStatusID = ? WHERE carID = ?";
        db.update(sql, car.getBrand(), car.getModel(), car.getYear(), car.getCarID(), car.getMileage(),
                car.getFuelType(), car.getHealthStatusID(), car.getCarID());
    }

}
