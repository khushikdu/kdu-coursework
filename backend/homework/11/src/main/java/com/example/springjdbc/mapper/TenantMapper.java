package com.example.springjdbc.mapper;

import com.example.springjdbc.entities.Tenant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TenantMapper implements RowMapper<Tenant> {
    /**
     * to map the row for tenant and display
     * @param resultSet: object of class ResultSet
     * @param row: row number
     * @return : Tenant object containing all details
     * throws SQLException
     */
    @Override
    public Tenant mapRow(ResultSet resultSet, int row) throws SQLException {
        return new Tenant(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name"),
                resultSet.getDate("created_at"),
                resultSet.getDate("updated_at"),
                resultSet.getString("created_by"),
                resultSet.getString("updated_by")
        );
    }
}
