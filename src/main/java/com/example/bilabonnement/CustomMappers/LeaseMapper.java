package com.example.bilabonnement.CustomMappers;

import com.example.bilabonnement.Model.Lease;
import com.example.bilabonnement.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaseMapper implements RowMapper<Lease> {
    public Lease mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lease lease = new Lease();
        lease.setLeaseID(rs.getInt("lease_id"));
        lease.setUserID(rs.getInt("user_id"));
        lease.setEmployeeID(rs.getInt("employee_id"));
        lease.setCarID(rs.getInt("car_id"));
        lease.setLocationReturnID(rs.getInt("location_return"));
        lease.setLocationPickupID(rs.getInt("location_pickup"));
        lease.setPrice(rs.getDouble("price"));
        lease.setMaxMileage(rs.getInt("max_mileage"));
        lease.setDateStart(rs.getString("date_start"));
        lease.setDateReturn(rs.getString("date_return"));
        lease.setDateEnd(rs.getString("date_end"));
        return lease;
    }
}
