package com.example.bilabonnement.CustomMappers;

import com.example.bilabonnement.Model.DamageReport;
import com.example.bilabonnement.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DamageReportMapper implements RowMapper<DamageReport> {
    public DamageReport mapRow(ResultSet rs, int rowNum) throws SQLException {
        DamageReport damageReport = new DamageReport();
        damageReport.setReportID(rs.getInt("report_id"));
        damageReport.setDescription(rs.getString("description"));
        damageReport.setLeaseID(rs.getInt("lease_id"));
        damageReport.setEmployeeID(rs.getInt("employee_id"));
        damageReport.setCost(rs.getDouble("cost"));
        damageReport.setDateReport(rs.getString("date_report"));
        damageReport.setDateAccident(rs.getString("date_accident"));
        return damageReport;
    }
}
