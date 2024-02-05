package com.example.springjdbc.respository;

import com.example.springjdbc.dto.ShiftTypeDTO;
import com.example.springjdbc.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShiftTypeDAO {
    Logging.LoggerType loggerTypeError = Logging.LoggerType.ERROR;
    Logging.LoggerType loggerTypeInfo = Logging.LoggerType.INFO;

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ShiftTypeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /**
     * to insert value into the database
     * @param shiftTypeDTO: object containing the data to be inserted
     */
    public void add(ShiftTypeDTO shiftTypeDTO) {
        try {
            Logging.printLogger("Adding valued to the database",loggerTypeInfo);
            String sqlQuery = "insert into shift_type(id, uq_name, description," +
                    "active, time_zone, tenant_id, created_at, updated_at," +
                    "created_by, updated_by" +
                    "  values(?,?,?,?,?,?,?,?,?,?)";

            jdbcTemplate.update(sqlQuery,
                    shiftTypeDTO.getId(),
                    shiftTypeDTO.getUqName(),
                    shiftTypeDTO.getDescription(),
                    shiftTypeDTO.getActive(),
                    shiftTypeDTO.getTimeZone(),
                    shiftTypeDTO.getTenantID(),
                    shiftTypeDTO.getCreatedAt(),
                    shiftTypeDTO.getUpdatedAt(),
                    shiftTypeDTO.getCreatedBy(),
                    shiftTypeDTO.getUpdatedBy());
        } catch (Exception e){
            Logging.printLogger("Error Adding Shift : "+e.getMessage(),loggerTypeError);
        }
    }
}
