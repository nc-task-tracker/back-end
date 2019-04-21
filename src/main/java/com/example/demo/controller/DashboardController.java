package com.example.demo.controller;

import com.example.demo.dto.DashboardDto;
import com.example.demo.model.Dashboard;
import com.example.demo.service.DashboardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    private final ModelMapper modelMapper;

    @Autowired
    public DashboardController(DashboardService dashboardService,
                               ModelMapper modelMapper) {
        this.dashboardService = dashboardService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{id}")
    public DashboardDto getDashboardById(@PathVariable(name = "id") String id) {
        return modelMapper.map(dashboardService.getDashboardById(id), DashboardDto.class);
    }

    @GetMapping(value = "/all")
    public List<DashboardDto> getAllDashboards() {
        List<DashboardDto> dashboardsDto = new ArrayList<>();
        List<Dashboard> dashboards = dashboardService.getAllDashboards();
        for(Dashboard item : dashboards) {
            dashboardsDto.add(modelMapper.map(item, DashboardDto.class));
        }
        return dashboardsDto;
    }

    @PostMapping
    public Dashboard saveDashboard(@RequestBody Dashboard dashboard) {
        return dashboardService.saveDashboard(dashboard);
    }

    @PutMapping
    public DashboardDto updateDashboard(@RequestBody DashboardDto dashboardForUpdate) {
        Dashboard dashboard = modelMapper.map(dashboardService.getDashboardById(dashboardForUpdate.getId()), Dashboard.class);
        return modelMapper.map(dashboardService.updateDashboard(dashboard), DashboardDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteDashboard(@PathVariable(name = "id") String id) {
        dashboardService.deleteDashboard(id);
        return ResponseEntity.noContent().build();
    }
}
