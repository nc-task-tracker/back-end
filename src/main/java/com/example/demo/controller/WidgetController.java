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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/widget")
public class WidgetController {
    private final WidgetService widgetService;

    private final ModelMapper modelMapper;

    @Autowired
    public WidgetController(WidgetService widgetService,
                            ModelMapper modelMapper) {
        this.widgetService = widgetService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{id}")
    public WidgetDto getWidgetById(@PathVariable(name = "id") String id) {
        return modelMapper.map(widgetService.getWidgetById(id), WidgetDto.class);
    }

    @GetMapping
    public List<WidgetDto> getAllWidgets() {
        List<WidgetDto> widgetsDto = new ArrayList<>();
        List<Widget> widgets = widgetService.getAllWidgets();
        for(Widget item : widgets) {
            widgetsDto.add(modelMapper.map(item, WidgetDto.class));
        }
        return widgetsDto;
    }

    @PostMapping
    public WidgetDto createWidget(@RequestBody WidgetDto widgetDto) {
        return modelMapper.map(widgetService.create(modelMapper.map(widgetDto, Widget.class)), WidgetDto.class);
    }

    @PutMapping
    public WidgetDto updateDashboard(@RequestBody WidgetDto widgetDto) {
        Widget widget = modelMapper.map(widgetService.getWidgetById(widgetDto.getId()), Widget.class);
        return modelMapper.map(widgetService.update(widget), WidgetDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteDashboard(@PathVariable(name = "id") String id) {
        widgetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
