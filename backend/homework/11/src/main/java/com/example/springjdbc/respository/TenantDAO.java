package com.example.springjdbc.respository;

import com.example.springjdbc.dto.AllTenantsDTO;
import com.example.springjdbc.dto.TenantDTO;
import com.example.springjdbc.entities.Tenant;
import com.example.springjdbc.logging.Logging;
import com.example.springjdbc.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TenantDAO {
    Logging.LoggerType loggerTypeInfo = Logging.LoggerType.INFO;
    Logging.LoggerType loggerTypeError = Logging.LoggerType.ERROR;
    private final JdbcTemplate jdbcTemplate;
    private final UserDAO userDAO;
    private final ShiftTypeDAO shiftTypeDAO;
    private final ShiftUserDAO shiftUserDAO;
    private final ShiftDAO shiftDAO;
    @Autowired
    public TenantDAO(JdbcTemplate jdbcTemplate, UserDAO userDAO, ShiftTypeDAO shiftTypeDAO, ShiftUserDAO shiftUserDAO, ShiftDAO shiftDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDAO = userDAO;
        this.shiftTypeDAO = shiftTypeDAO;
        this.shiftUserDAO = shiftUserDAO;
        this.shiftDAO = shiftDAO;
    }
    /**
     * to insert value into the database
     * @param tenantDTO: object containing the data to be inserted
     */
    @Transactional
    public int add(TenantDTO tenantDTO){
        try{
            String sqlQuery="insert into tenants (id, name, created_by, updated_by) " +
                    "values (?, ?, ?, ?)";
            return jdbcTemplate.update(sqlQuery,
                    tenantDTO.getId(),
                    tenantDTO.getName(),
                    tenantDTO.getCreatedBy(),
                    tenantDTO.getUpdatedBy());
        } catch(Exception e){
            Logging.printLogger("Errror adding tenant :"+e.getMessage(),loggerTypeError);
            throw e;
        }
    }
    /**
     * to insert value into the database
     * @param allTenantsDTO: object containing the data to be inserted
     */
    @Transactional
    public void addAllTenants(AllTenantsDTO allTenantsDTO){
        try{
            Logging.printLogger("Adding all tenants",loggerTypeInfo);
            userDAO.add(allTenantsDTO.getUserDTO());
            shiftTypeDAO.add(allTenantsDTO.getShiftTypeDTO());
            shiftUserDAO.add(allTenantsDTO.getShiftUserDTO());
            shiftDAO.add(allTenantsDTO.getShiftDTO());
        }catch (Exception e){
            Logging.printLogger("Error wile adding : "+e.getMessage(),loggerTypeError);
        }
    }

    /**
     * to get the list of all the tenants
     * @return : list of tenants
     */
    public List<Tenant> getAllTenant() {
        String sqlQuery="select * from tenants";
        return jdbcTemplate.query(sqlQuery,new TenantMapper());
    }
}
