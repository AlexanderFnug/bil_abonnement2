package com.example.bilabonnement.CustomMappers;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.CarModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarModelMapper implements RowMapper<CarModel> {
    public CarModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        CarModel carModel = new CarModel();
        carModel.setModelID(rs.getInt("model_id"));
        carModel.setName(rs.getString("name"));
        carModel.setFuelType(rs.getString("fuel_type"));
        return carModel;
    }
}
