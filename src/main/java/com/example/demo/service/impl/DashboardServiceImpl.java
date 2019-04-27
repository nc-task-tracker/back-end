package com.example.demo.service.impl;

import com.example.demo.dto.DashboardDto;
import com.example.demo.model.Dashboard;
import com.example.demo.repository.DashboardRepository;
import com.example.demo.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private DashboardRepository repository;

    @Autowired
    public DashboardServiceImpl(DashboardRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dashboard addDashboard(Dashboard dashboard) {
        return repository.save(dashboard);
    }

    @Override
    public Dashboard getDashboardById(String id) {
        return repository.findDashboardById(id);
    }

    @Override
    public Dashboard updateDashboard(Dashboard dashboard) {
        return repository.save(dashboard);
    }

    @Override
    public List<Dashboard> getAllDashboards() {
        return (List<Dashboard>) repository.findAll();
    }

    @Override
    public void deleteDashboard(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Dashboard> getAllDashboardByUserId(String id) {
        return (List<Dashboard>) repository.findAllByUser(id);
    }
}
