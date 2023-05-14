package com.example.bilabonnement.CustomMappers;
import com.example.bilabonnement.Component.BasicInfoComponent;
import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {

    @Autowired
    BasicInfoComponent basicInfo;

    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setCarID(rs.getInt("car_id"));
        car.setMileage(rs.getInt("mileage"));
        car.setYear(rs.getInt("year"));
        car.setModelID(rs.getInt("model_id"));
        car.setStatusID(rs.getInt("status_id"));
        car.setModelName(rs.getString("name"));
        car.setBrand(rs.getString("brand"));
        car.setFuelTypeID(rs.getInt("fuel_type_id"));
        return car;
    }
}
