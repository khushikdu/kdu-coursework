package com.example.springjdbc.respository;

import com.example.springjdbc.dto.ShiftDTO;
import com.example.springjdbc.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShiftDAO {
    Logging.LoggerType loggerTypeError = Logging.LoggerType.ERROR;

    Logging.LoggerType loggerTypeInfo = Logging.LoggerType.INFO;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ShiftDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * to insert value into the database
     * @param shiftDTO: object containing the data to be inserted
     */
    public void add(ShiftDTO shiftDTO) {
        try {
            Logging.printLogger("Adding values to the database ",loggerTypeInfo);
            String sqlQuery = "insert into shifts(id, shift_type_id, name, start_date," +
                    "        end_date, start_time, end_time, created_at, updated_at," +
                    "        created_by, updated_by)" +
                    "        values(?,?,?,?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sqlQuery,
                    shiftDTO.getId(),
                    shiftDTO.getShiftTypeID(),
                    shiftDTO.getName(),
                    shiftDTO.getStartDate(),
                    shiftDTO.getEndDate(),
                    shiftDTO.getStartTime(),
                    shiftDTO.getEndTime(),
                    shiftDTO.getCreatedAt(),
                    shiftDTO.getUpdatedAt(),
                    shiftDTO.getCreatedBy(),
                    shiftDTO.getUpdatedBy());
        }catch (Exception e){
            Logging.printLogger("Error Adding Shift : "+e.getMessage(),loggerTypeError);
        }
    }
}
