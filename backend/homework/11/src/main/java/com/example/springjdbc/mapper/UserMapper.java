package com.example.springjdbc.mapper;

import com.example.springjdbc.entities.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class UserMapper implements RowMapper<Users> {
    /**
     * to map the row for User table and display
     * @param resultSet: object of class ResultSet
     * @param row: row number
     * @return : users object containing all details
     * throws SQLException
     */
    @Override
    public Users mapRow(ResultSet resultSet, int row) throws SQLException{
        return new Users(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("user_name"),
                resultSet.getInt("logged_in"),
                resultSet.getString("time_zone"),
                UUID.fromString(resultSet.getString("tenant_id")),
                resultSet.getDate("created_by"),
                resultSet.getDate("updated_at"),
                resultSet.getString("created_by"),
                resultSet.getString("updated_by")
        );
    }
}
