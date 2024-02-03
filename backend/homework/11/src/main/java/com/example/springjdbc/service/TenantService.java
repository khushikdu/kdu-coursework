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
    private TenantDAO tenantDAO;
    @Autowired
    public TenantService(TenantDAO tenantDAO) {
        this.tenantDAO = tenantDAO;
    }
    @Transactional
    public void addAllServiceAtOnce(AllTenantsDTO allTenantsDTO){
        tenantDAO.addAllTenants(allTenantsDTO);
    }
    @Transactional
    public int addService(TenantDTO tenantDTO) {
        return tenantDAO.add(tenantDTO);
    }
    public List<Tenant> getAllTenant() {
        return tenantDAO.getAllTenant();
    }
}
