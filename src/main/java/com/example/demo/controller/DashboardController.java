package com.example.demo.controller;

import com.example.demo.dto.DashboardDto;
import com.example.demo.dto.WidgetDto;
import com.example.demo.model.Dashboard;
import com.example.demo.model.Widget;
import com.example.demo.service.DashboardService;
import com.example.demo.service.WidgetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    private final WidgetService widgetService;

    private final ModelMapper modelMapper;

    @Autowired
    public DashboardController(DashboardService dashboardService,
                               ModelMapper modelMapper,
                               WidgetService widgetService) {
        this.dashboardService = dashboardService;
        this.modelMapper = modelMapper;
        this.widgetService = widgetService;
    }

    @GetMapping(value = "/{idDashboard}")
    public DashboardDto getDashboardById(@PathVariable(name = "idDashboard") String id) {
        return modelMapper.map(dashboardService.getDashboardById(id), DashboardDto.class);
    }

    @GetMapping(value = "/{userID}/allDashboards")
    public List<DashboardDto> getAllDashboardsByUserId(@PathVariable(name = "userID") String id) {
        List<DashboardDto> dashboardsDto = new ArrayList<>();
        List<Dashboard> dashboards = dashboardService.getAllDashboardByUserId(id);
        for(Dashboard item : dashboards) {
            dashboardsDto.add(modelMapper.map(item, DashboardDto.class));
        }
        return dashboardsDto;
    }

    @GetMapping(value = "/{dashboardID}/allWidgets")
    public List<WidgetDto> getAllWidgetDashboardById(@PathVariable(name = "dashboardID") String id) {
        List<WidgetDto> widgetDto = new ArrayList<>();
        List<Widget> widgets = widgetService.getAllWidgetsById(id);
        for(Widget item : widgets) {
            widgetDto.add(modelMapper.map(item, WidgetDto.class));
        }
        return widgetDto;
    }

    @PostMapping(value = "/add")
    public DashboardDto createDashboard(@RequestBody DashboardDto dashboardDto) {
        return modelMapper.map(dashboardService.create(modelMapper.map(dashboardDto, Dashboard.class)), DashboardDto.class);
    }

    @PutMapping
    public DashboardDto updateDashboard(@RequestBody @Valid DashboardDto dashboardForUpdate) {
        Dashboard dashboard = modelMapper.map(dashboardService.getDashboardById(dashboardForUpdate.getId()), Dashboard.class);
        return modelMapper.map(dashboardService.update(dashboard), DashboardDto.class);
    }

    @DeleteMapping(value = "/{dashboardID}/delete")
    public ResponseEntity deleteDashboard(@PathVariable(name = "dashboardID") String id) {
        dashboardService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
