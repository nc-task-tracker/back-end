package com.example.demo.service;

import com.example.demo.model.Dashboard;

import java.util.List;

public interface DashboardService {
    Dashboard saveDashboard(Dashboard dashboard);
    Dashboard getDashboardById(String id);
    Dashboard updateDashboard(Dashboard dashboard);
    List<Dashboard> getAllDashboards();
    void deleteDashboard(String id);
}
