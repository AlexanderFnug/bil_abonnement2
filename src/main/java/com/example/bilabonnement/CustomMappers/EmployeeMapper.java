package com.example.bilabonnement.CustomMappers;

import com.example.bilabonnement.Model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee tempEmp = new Employee();
        tempEmp.setEmployeeID(rs.getInt("employee_id"));
        tempEmp.setUserID(rs.getInt("user_id"));
        tempEmp.setPassword(rs.getString("password"));
        tempEmp.setPositionID(rs.getInt("position_id"));
        tempEmp.setSalary(rs.getDouble("salary"));
        tempEmp.setFirstName(rs.getString("first_name"));
        tempEmp.setLastName(rs.getString("last_name"));
        tempEmp.setEmail(rs.getString("email"));
        tempEmp.setPhoneNumber(rs.getString("phone_number"));
        tempEmp.setAddress(rs.getString("address"));
        return tempEmp;
    }
}
