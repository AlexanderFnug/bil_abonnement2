package com.example.bilabonnement.CustomMappers;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setCarID(rs.getInt("car_id"));
        car.setMileage(rs.getInt("mileage"));
        car.setYear(rs.getInt("year"));
        car.setModelID(rs.getInt("model_id"));
        car.setStatusID(rs.getInt("status_id"));
        return car;
    }
}
