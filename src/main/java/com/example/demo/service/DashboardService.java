package com.example.demo.service;

import com.example.demo.model.Dashboard;

import java.util.List;

public interface DashboardService {
    Dashboard create(Dashboard dashboard);
    Dashboard getDashboardById(String id);
    Dashboard update(Dashboard dashboard);
    List<Dashboard> getAllDashboards();
    void delete(String id);
    List<Dashboard> getAllDashboardByUserId(String id);
}
