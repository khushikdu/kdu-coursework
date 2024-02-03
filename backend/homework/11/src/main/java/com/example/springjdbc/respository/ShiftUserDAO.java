package com.example.springjdbc.respository;

import com.example.springjdbc.dto.ShiftUserDTO;
import com.example.springjdbc.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShiftUserDAO {
    Logging.LoggerType loggerTypeError = Logging.LoggerType.ERROR;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ShiftUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /**
     * to insert value into the database
     * @param shiftUserDTO: object containing the data to be inserted
     */
    public void add(ShiftUserDTO shiftUserDTO){
        try {
            String sqlQuery = "insert into shift_users (id, shift_id, user_id, tenant_id" +
                    "created_at, updated_at, created_by, updated_by" +
                    "values(?,?,?,?,?,?,?,?)";

            jdbcTemplate.update(sqlQuery,
                    shiftUserDTO.getId(),
                    shiftUserDTO.getShiftID(),
                    shiftUserDTO.getUserID(),
                    shiftUserDTO.getTenantID(),
                    shiftUserDTO.getCreatedAt(),
                    shiftUserDTO.getUpdatedAt(),
                    shiftUserDTO.getCreatedBy(),
                    shiftUserDTO.getUpdatedBy());
        }catch (Exception e){
            Logging.printLogger("Error Adding Shift : "+e.getMessage(),loggerTypeError);
        }
    }
}
