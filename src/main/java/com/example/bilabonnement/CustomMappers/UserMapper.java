package com.example.bilabonnement.CustomMappers;

import com.example.bilabonnement.Model.Employee;
import com.example.bilabonnement.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Why: We had issues with BeanPropertyRowMapper not being able to match column names like 'first_name' with
 * the variable 'firstName', even though spring documentation lists conversion to camel case as a feature.
 * Solution: We quickly created a few custom mappers using the RowMapper interface.
 */

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserID(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setAddress(rs.getString("address"));
        user.setPhoneNumber(rs.getString("phone_number"));
        return user;
    }
}
