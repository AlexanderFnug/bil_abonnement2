package com.example.bilabonnement.CustomMappers;

import com.example.bilabonnement.Model.CarModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Why: We had issues with BeanPropertyRowMapper not being able to match column names like 'first_name' with
 * the variable 'firstName', even though spring documentation lists conversion to camel case as a feature.
 * Solution: We quickly created a few custom mappers using the RowMapper interface.
 */

public class CarModelMapper implements RowMapper<CarModel> {
    public CarModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        CarModel carModel = new CarModel();
        carModel.setModelID(rs.getInt("model_id"));
        carModel.setModelName(rs.getString("name"));
        carModel.setFuelTypeID(rs.getInt("fuel_type_id"));
        return carModel;
    }
}
