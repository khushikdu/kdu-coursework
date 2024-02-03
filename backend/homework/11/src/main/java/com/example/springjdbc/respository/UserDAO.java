package com.example.springjdbc.respository;

import com.example.springjdbc.dto.UserDTO;
import com.example.springjdbc.entities.Users;
import com.example.springjdbc.logging.Logging;
import com.example.springjdbc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    Logging.LoggerType loggerTypeInfo = Logging.LoggerType.INFO;
    Logging.LoggerType loggerTypeError = Logging.LoggerType.ERROR;

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int add(UserDTO userDTO){
        try {
            Logging.printLogger("Adding user",loggerTypeInfo);
            String sqlQuery = "insert into users (id, user_name, logged_in, time_zone, " +
                    "tenant_id, created_at, updated_at, created_by, updated_by) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            return jdbcTemplate.update(sqlQuery,
                    userDTO.getId(),
                    userDTO.getUsername(),
                    userDTO.getLogin(),
                    userDTO.getTimezone(),
                    userDTO.getTenantID(),
                    userDTO.getCreatedAt(),
                    userDTO.getUpdatedAt(),
                    userDTO.getCreatedBy(),
                    userDTO.getUpdatedBy());
        } catch (Exception e) {
            Logging.printLogger("Error Adding User : "+e.getMessage(),loggerTypeError);
            throw e;
        }
    }
    public List<Users> getUsers(){
        String sqlQuery="select * from users";
        return jdbcTemplate.query(sqlQuery,new UserMapper());
    }
}
