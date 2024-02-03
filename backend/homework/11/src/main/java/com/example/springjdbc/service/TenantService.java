package com.example.springjdbc.service;

import com.example.springjdbc.dto.AllTenantsDTO;
import com.example.springjdbc.dto.TenantDTO;
import com.example.springjdbc.entities.Tenant;
import com.example.springjdbc.respository.TenantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantService {
    private final TenantDAO tenantDAO;
    @Autowired
    public TenantService(TenantDAO tenantDAO) {
        this.tenantDAO = tenantDAO;
    }
    /**
     * to add the dto object in the repository class
     * @param allTenantsDTO : data transfer object
     */
    @Transactional
    public void addAllServiceAtOnce(AllTenantsDTO allTenantsDTO){
        tenantDAO.addAllTenants(allTenantsDTO);
    }
    /**
     * to add the dto object in the repository class
     * @param tenantDTO : data transfer object
     * @return : status for adding
     */
    @Transactional
    public int addService(TenantDTO tenantDTO) {
        return tenantDAO.add(tenantDTO);
    }
    /**
     * to get the list of all users
     * @return : list of tenants
     */
    public List<Tenant> getAllTenant() {
        return tenantDAO.getAllTenant();
    }
}
